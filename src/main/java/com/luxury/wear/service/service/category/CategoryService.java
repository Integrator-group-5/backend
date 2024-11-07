package com.luxury.wear.service.service.category;

import com.luxury.wear.service.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category updateCategory(Long id, Category category);

    void deleteCategoryById(Long id);
}
