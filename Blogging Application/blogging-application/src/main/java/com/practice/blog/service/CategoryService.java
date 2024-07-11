package com.practice.blog.service;

import com.practice.blog.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(String id, CategoryDto categoryDto);

    CategoryDto getCategoryById(String id);

    void deleteById(String id);
}
