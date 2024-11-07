package com.luxury.wear.service.service.category;

import com.luxury.wear.service.entity.Category;
import com.luxury.wear.service.entity.Image;
import com.luxury.wear.service.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private static final String CATEGORY_NOT_FOUND_ID = "Category not found with Id: ";

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CATEGORY_NOT_FOUND_ID + id));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CATEGORY_NOT_FOUND_ID + id));

        return updateExistingCategory(existingCategory, category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CATEGORY_NOT_FOUND_ID + id));
        categoryRepository.deleteById(id);
    }

    private Category updateExistingCategory(Category existingCategory, Category category) {
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setCover(category.getCover());
        return categoryRepository.save(existingCategory);
    }
}
