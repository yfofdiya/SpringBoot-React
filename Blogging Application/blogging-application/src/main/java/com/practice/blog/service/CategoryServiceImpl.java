package com.practice.blog.service;

import com.practice.blog.dto.CategoryDto;
import com.practice.blog.entity.Category;
import com.practice.blog.exception.ResourceNotFoundException;
import com.practice.blog.repository.CategoryRepository;
import com.practice.blog.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private static final Converter<CategoryDto, Category> converter = new Converter<>(CategoryDto.class, Category.class);

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        categoryDto.setId(UUID.randomUUID().toString());
        Category category = converter.convertToEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return converter.convertToDto(savedCategory);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(converter::convertToDto).toList();
    }

    @Override
    public CategoryDto updateCategory(String id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", " Id", id));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = categoryRepository.save(category);
        return converter.convertToDto(updatedCategory);
    }

    @Override
    public CategoryDto getCategoryById(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", " Id", id));
        return converter.convertToDto(category);
    }

    @Override
    public void deleteById(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", " Id", id));
        categoryRepository.delete(category);
    }
}
