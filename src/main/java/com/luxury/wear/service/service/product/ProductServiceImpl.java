package com.luxury.wear.service.service.product;

import com.luxury.wear.service.commons.Constants;
import com.luxury.wear.service.dto.product.ProductRequestDto;
import com.luxury.wear.service.dto.product.ProductResponseDto;
import com.luxury.wear.service.entity.Category;
import com.luxury.wear.service.entity.Product;
import com.luxury.wear.service.entity.Reservation;
import com.luxury.wear.service.entity.Size;
import com.luxury.wear.service.exception.EntityAlreadyExistsException;
import com.luxury.wear.service.exception.ResourceNotFoundException;
import com.luxury.wear.service.mapper.ProductMapper;
import com.luxury.wear.service.repository.ProductRepository;
import com.luxury.wear.service.repository.UserRepository;
import com.luxury.wear.service.service.category.CategoryService;
import com.luxury.wear.service.service.size.SizeService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;
    private final SizeService sizeService;


    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        validateProductNameUniqueness(productRequestDto.getName());
        productRepository.findByReference(productRequestDto.getReference()).ifPresent(existing -> {
            throw new EntityAlreadyExistsException(Constants.ERROR_PRODUCT_ALREADY_EXISTS_REFERENCE + productRequestDto.getReference());
        });

        Category existingCategory = categoryService.getCategoryById(productRequestDto.getCategory().getId());
        if (existingCategory == null) {
            throw new ResourceNotFoundException(Constants.ERROR_CATEGORY_NOT_FOUND_ID + productRequestDto.getCategory().getId());
        }

        Product product = productMapper.toEntity(productRequestDto);
        product.getImages().forEach(image -> image.setProduct(product));

        Product savedProduct = productRepository.save(product);
        return productMapper.toResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND_ID + id));
        return productMapper.toResponseDto(product);
    }

    @Override
    public ProductResponseDto getProductByReference(String reference) {
        Product product = productRepository.findByReference(reference)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND_REFERENCE + reference));
        return productMapper.toResponseDto(product);
    }

    @Override
    public ProductResponseDto getProductByName(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND_NAME + name));
        return productMapper.toResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponseDto)
                .toList();
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(Pageable pageable) {
        return productRepository.findAllByDeletedFalse(pageable)
                .map(productMapper::toResponseDto);
    }

    @Override
    public List<ProductResponseDto> getAllTopProducts() {
        return productRepository.findAllTopProductsByDeletedFalse(PageRequest.of(0, 6))
                .stream()
                .map(productMapper::toResponseDto)
                .toList();
    }

    @Override
    public Page<ProductResponseDto> getProductsByCategory(String categoryName, Pageable pageable) {
        return productRepository.findByCategory_NameAndDeletedFalse(categoryName, pageable)
                .map(productMapper::toResponseDto);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND_ID + id));

        fillMissingFields(productRequestDto, existingProduct);
        validateUpdateProductNameUniqueness(productRequestDto.getName(), id);
        validateUpdateProductReferenceUniqueness(productRequestDto.getReference(), id);
        validateCategoryExistence(productRequestDto.getCategory().getId());

        if (productRequestDto.getSizes() != null && !productRequestDto.getSizes().isEmpty()) {
            existingProduct.clearSizes();
            productRequestDto.getSizes().forEach(size -> {
                Size existingSize = sizeService.getSizeById(size.getId());
                existingProduct.addSize(existingSize);
            });
        }
        if (productRequestDto.getImages() != null && !productRequestDto.getImages().isEmpty()) {
            existingProduct.clearImages();
            productRequestDto.getImages().forEach(image -> image.setProduct(existingProduct));
            productRequestDto.getImages().forEach(image -> existingProduct.addImage(image));
        }

        Product updatedProduct = productMapper.updateEntity(existingProduct, productRequestDto);
        Product savedProduct = productRepository.save(updatedProduct);
        return productMapper.toResponseDto(savedProduct);
    }

    @Transactional
    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        // Mark the product as deleted
        product.setDeleted(true);
        productRepository.save(product);

        // Remove the product from all users' favorites
        userRepository.removeProductFromFavorites(id);
    }

    @Override
    public Page<ProductResponseDto> getAvailableProducts(LocalDate startDate, LocalDate endDate, String searchString, Pageable pageable) {
        Specification<Product> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

        // Exclude Deleted Products
        predicates.add(cb.isFalse(root.get("deleted")));

            // Search String Predicate
            if (searchString != null && !searchString.trim().isEmpty()) {
                String pattern = "%" + searchString.trim().toLowerCase() + "%";

                List<Predicate> searchPredicates = new ArrayList<>();
                searchPredicates.add(cb.like(cb.lower(root.get("name")), pattern));
                searchPredicates.add(cb.like(cb.lower(root.get("reference")), pattern));
                searchPredicates.add(cb.like(cb.lower(root.get("description")), pattern));
                searchPredicates.add(cb.like(cb.lower(root.get("material")), pattern));
                searchPredicates.add(cb.like(cb.lower(root.get("color")), pattern));
                searchPredicates.add(cb.like(cb.lower(root.get("designer")), pattern));

                // Join with Category
                Join<Product, Category> categoryJoin = root.join("category", JoinType.LEFT);
                searchPredicates.add(cb.like(cb.lower(categoryJoin.get("name")), pattern));

                // Join with Sizes
//                Join<Product, Size> sizeJoin = root.joinList("sizes", JoinType.LEFT);
                Join<Product, Size> sizeJoin = root.join("sizes", JoinType.LEFT);
                searchPredicates.add(cb.like(cb.lower(sizeJoin.get("size")), pattern));

                predicates.add(cb.or(searchPredicates.toArray(new Predicate[0])));
            }

            // Availability Predicate
            if (startDate != null && endDate != null) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<Reservation> reservationRoot = subquery.from(Reservation.class);
                subquery.select(reservationRoot.get("product").get("productId"));
                Predicate reservationOverlap = cb.and(
                        cb.equal(reservationRoot.get("product").get("productId"), root.get("productId")),
                        cb.lessThanOrEqualTo(reservationRoot.get("startDate"), endDate),
                        cb.greaterThanOrEqualTo(reservationRoot.get("endDate"), startDate)
                );
                subquery.where(reservationOverlap);

                predicates.add(cb.not(root.get("productId").in(subquery)));
            }

            assert query != null;
            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return productRepository.findAll(specification, pageable).map(productMapper::toResponseDto);
    }

    @Override
    public Set<String> extractKeywords() {
        List<Product> products = productRepository.findAll();

        Set<String> keywords = new HashSet<>();

        for (Product product : products) {
            // Add the full product name
            if (product.getName() != null) {
                keywords.add(product.getName().toLowerCase());
            }

            // Add tokenized words from the product name
            keywords.addAll(tokenize(product.getName()));

            // Add other attributes: material, color, designer, category
            Optional.ofNullable(product.getMaterial()).ifPresent(material -> keywords.add(material.toLowerCase()));
            Optional.ofNullable(product.getColor()).ifPresent(color -> keywords.add(color.toLowerCase()));
            Optional.ofNullable(product.getDesigner()).ifPresent(designer -> keywords.add(designer.toLowerCase()));
            if (product.getCategory() != null && product.getCategory().getName() != null) {
                keywords.add(product.getCategory().getName().toLowerCase());
            }
        }

        // Filter for unique and relevant keywords
        return keywords.stream()
                .filter(keyword -> keyword.length() > 2) // Remove very short words
                .collect(Collectors.toSet());
    }

    /**
     * Tokenizes a string into individual words, avoiding null issues.
     *
     * @param input The input string.
     * @return A list of tokenized words.
     */
    private List<String> tokenize(String input) {
        if (input == null || input.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(input.toLowerCase().split("\\s+")) // Split by spaces
                .filter(word -> word.length() > 2) // Remove short tokens
                .collect(Collectors.toList());
    }

    private void validateProductNameUniqueness(String productName) {
        productRepository.findByName(productName).ifPresent(existing -> {
            throw new EntityAlreadyExistsException(Constants.ERROR_PRODUCT_ALREADY_EXISTS_NAME + productName);
        });
    }

    private void validateUpdateProductReferenceUniqueness(String productReference, Long id) {
        Product existingProduct = productRepository.findByReference(productReference).orElse(null);
        if (existingProduct != null && !existingProduct.getProductId().equals(id)) {
            throw new EntityAlreadyExistsException(Constants.ERROR_PRODUCT_ALREADY_EXISTS_REFERENCE + productReference);
        }
    }

    private void validateUpdateProductNameUniqueness(String productName, Long id) {
        Product existingProduct = productRepository.findByName(productName).orElse(null);
        if (existingProduct != null && !existingProduct.getProductId().equals(id)) {
            throw new EntityAlreadyExistsException(Constants.ERROR_PRODUCT_ALREADY_EXISTS_NAME + productName);
        }
    }

    private void validateCategoryExistence(Long categoryId) {
        Category existingCategory = categoryService.getCategoryById(categoryId);
        if (existingCategory == null) {
            throw new ResourceNotFoundException(Constants.ERROR_CATEGORY_NOT_FOUND_ID + categoryId);
        }
    }

    private void fillMissingFields(ProductRequestDto productRequestDto, Product existingProduct) {
        if (productRequestDto.getName() == null) {
            productRequestDto.setName(existingProduct.getName());
        }
        if (productRequestDto.getReference() == null) {
            productRequestDto.setReference(existingProduct.getReference());
        }
        if (productRequestDto.getDescription() == null) {
            productRequestDto.setDescription(existingProduct.getDescription());
        }
        if (productRequestDto.getMaterial() == null) {
            productRequestDto.setMaterial(existingProduct.getMaterial());
        }
        if (productRequestDto.getColor() == null) {
            productRequestDto.setColor(existingProduct.getColor());
        }
        if (productRequestDto.getDesigner() == null) {
            productRequestDto.setDesigner(existingProduct.getDesigner());
        }
        if (productRequestDto.getPrice() == null) {
            productRequestDto.setPrice(existingProduct.getPrice());
        }
        if (productRequestDto.getCategory() == null) {
            productRequestDto.setCategory(existingProduct.getCategory());
        }
        if (productRequestDto.getSizes() == null) {
            productRequestDto.setSizes(existingProduct.getSizes());
        }
        if (productRequestDto.getImages() == null) {
            productRequestDto.setImages(existingProduct.getImages());
        }
    }
}
