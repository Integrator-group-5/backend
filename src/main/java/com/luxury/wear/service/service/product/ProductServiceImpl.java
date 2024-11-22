package com.luxury.wear.service.service.product;

import com.luxury.wear.service.commons.Constants;
import com.luxury.wear.service.dto.product.ProductRequestDto;
import com.luxury.wear.service.dto.product.ProductResponseDto;
import com.luxury.wear.service.entity.Category;
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
        return productRepository.findAll(pageable)
                .map(productMapper::toResponseDto);
    }

    @Override
    public List<ProductResponseDto> getAllTopProducts() {
        return productRepository.findAllRandom(PageRequest.of(0, 6)).stream()
                .map(productMapper::toResponseDto)
                .toList();
    }

    @Override
    public Page<ProductResponseDto> getProductsByCategory(String categoryName, Pageable pageable) {
        return productRepository.findByCategory_Name(categoryName, pageable)
                .map(productMapper::toResponseDto);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND_ID + id));

        Category existingCategory = categoryService.getCategoryById(productRequestDto.getCategory().getId());
        if (existingCategory == null) {
            throw new ResourceNotFoundException(Constants.ERROR_CATEGORY_NOT_FOUND_ID + productRequestDto.getCategory().getId());
        }

        Product updatedProduct = productMapper.updateEntity(existingProduct, productRequestDto);
        Product savedProduct = productRepository.save(updatedProduct);
        return productMapper.toResponseDto(savedProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND_ID + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductResponseDto> getAvailableProducts(LocalDate startDate, LocalDate endDate, String search, Pageable pageable) {
        List<Long> unavailableProductIds = reservationRepository.findUnavailableProductIds(startDate, endDate);
        return productRepository.findAvailableProductsWithSearch(unavailableProductIds, search, pageable)
                .map(productMapper::toResponseDto);
    }

    private void validateProductNameUniqueness(String productName) {
        productRepository.findByName(productName).ifPresent(existing -> {
            throw new EntityAlreadyExistsException(Constants.ERROR_PRODUCT_ALREADY_EXISTS_NAME + productName);
        });
    }
}
