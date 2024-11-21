package com.luxury.wear.service.service.product;

import com.luxury.wear.service.commons.Constants;
import com.luxury.wear.service.dto.product.ProductRequestDto;
import com.luxury.wear.service.dto.product.ProductResponseDto;
import com.luxury.wear.service.entity.Category;
import com.luxury.wear.service.entity.Image;
import com.luxury.wear.service.entity.Product;
import com.luxury.wear.service.exception.EntityAlreadyExistsException;
import com.luxury.wear.service.exception.ResourceNotFoundException;
import com.luxury.wear.service.mapper.ProductMapper;
import com.luxury.wear.service.repository.ProductRepository;
import com.luxury.wear.service.repository.ReservationRepository;
import com.luxury.wear.service.service.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ReservationRepository reservationRepository;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {

        Product existingNameProduct = productRepository.findByName(productRequestDto.getName()).orElse(null);
        if (existingNameProduct != null) {
            throw new EntityAlreadyExistsException(Constants.ERROR_PRODUCT_ALREADY_EXISTS_NAME + productRequestDto.getName());
        }

        Product existingReferenceProduct = productRepository.findByReference(productRequestDto.getReference()).orElse(null);
        if (existingReferenceProduct != null) {
            throw new EntityAlreadyExistsException(Constants.ERROR_PRODUCT_ALREADY_EXISTS_REFERENCE + productRequestDto.getReference());
        }

        Category existingCategory = categoryService.getCategoryById(productRequestDto.getCategory().getId());
        if (existingCategory == null) {
            throw new ResourceNotFoundException(Constants.ERROR_CATEGORY_NOT_FOUND_ID + productRequestDto.getCategory().getId());
        }

        Product product = productMapper.toEntity(productRequestDto);

        for (Image image : productRequestDto.getImages()) {
            image.setProduct(product);
        }

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponseDto(savedProduct);
    }

    @Override
    public Product GetProductByID(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND_ID + id));
    }

    @Override
    public Product getProductByReference(String reference) {
        return productRepository.findByReference(reference)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND_REFERENCE + reference));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> getAllTopProducts() {
        return productRepository.findAllRandom(PageRequest.of(0, 6));
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND_ID + product.getProductId()));

        return updateExistingProduct(existingProduct, product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND_ID + id));

        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getAvailableProducts(LocalDate startDate, LocalDate endDate, String search, Pageable pageable) {
        // Retrieve unavailable product IDs for the given date range
        List<Long> unavailableProductIds = reservationRepository.findUnavailableProductIds(startDate, endDate);

        // Return available products matching the search criteria
        return productRepository.findAvailableProductsWithSearch(unavailableProductIds, search, pageable);
    }

    @Override
    public Page<Product> getProductsByCategory(String categoryName, Pageable pageable) {
        return productRepository.findByCategory_Name(categoryName, pageable);
    }

    public Product updateExistingProduct(Product existingProduct, Product newProductData) {
        Category existingCategory = categoryService.getCategoryById(newProductData.getCategory().getId());
        if (existingCategory == null) {
            throw new ResourceNotFoundException(Constants.ERROR_CATEGORY_NOT_FOUND_ID + newProductData.getCategory().getId());
        }

        existingProduct.setName(newProductData.getName());
        existingProduct.setReference(newProductData.getReference());
        existingProduct.setDescription(newProductData.getDescription());
        existingProduct.setMaterial(newProductData.getMaterial());
        existingProduct.setColor(newProductData.getColor());
        existingProduct.setDesigner(newProductData.getDesigner());
        existingProduct.setPrice(newProductData.getPrice());
        existingProduct.setImages(newProductData.getImages());
        existingProduct.setCategory(newProductData.getCategory());
        existingProduct.setSizes(newProductData.getSizes());
        List<Image> existingImages = existingProduct.getImages();
        List<Image> newImages = newProductData.getImages();
        // Eliminar imágenes que ya no están presentes
        existingImages.removeIf(image -> !newImages.contains(image));
        // Agregar nuevas imágenes y asociarlas con el producto
        for (Image newImage : newImages) {
            if (!existingImages.contains(newImage)) {
                newImage.setProduct(existingProduct);
                existingImages.add(newImage);
            }
        }
        existingProduct.setImages(existingImages);

        return productRepository.save(existingProduct);
    }
}
