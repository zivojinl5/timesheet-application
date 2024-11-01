package com.example.backend.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.backend.domain.model.model.Category;

public interface ICategoryService {

    List<Category> getAllCategories();

    Page<Category> getAllCategoriesWithPaginationAndSorting(PageRequest pageRequest);

    Category getCategoryById(Long id);

    Category createCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategoryById(Long id);

}