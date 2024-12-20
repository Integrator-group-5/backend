package com.luxury.wear.service.service.category;

import com.luxury.wear.service.commons.Constants;
import com.luxury.wear.service.entity.Category;
import com.luxury.wear.service.exception.ResourceNotFoundException;
import com.luxury.wear.service.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_CATEGORY_NOT_FOUND_ID + id));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_CATEGORY_NOT_FOUND_ID + id));

        return updateExistingCategory(existingCategory, category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_CATEGORY_NOT_FOUND_ID + id));
        categoryRepository.deleteById(id);
    }

    private Category updateExistingCategory(Category existingCategory, Category category) {
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setCover(category.getCover());
        return categoryRepository.save(existingCategory);
    }
}
