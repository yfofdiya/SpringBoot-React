package com.practice.blog.controller;

import com.practice.blog.dto.CategoryDto;
import com.practice.blog.service.CategoryService;
import com.practice.blog.util.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category Controller", description = "CRUD operation for Category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        log.info("Category creation started...");
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(categoryService.createCategory(categoryDto))
                .message(Map.of("success", "Category created successfully"))
                .success(true)
                .build();
        log.info("Category creation completed...");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategories() {
        log.info("Fetching all categories");
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(categoryService.getAllCategories())
                .message(Map.of("success", "All categories fetched"))
                .success(true)
                .build();
        log.info("Fetched all categories");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable("id") String id) {
        log.info("Fetching category for category id {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(categoryService.getCategoryById(id))
                .message(Map.of("success", "Category fetched by id " + id))
                .success(true)
                .build();
        log.info("Fetched category for category id {}", id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("id") String id, @Valid @RequestBody CategoryDto categoryDto) {
        log.info("Updating category for category id {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(categoryService.updateCategory(id, categoryDto))
                .message(Map.of("success", "Category updated successfully for id " + id))
                .success(true)
                .build();
        log.info("Updated category for category id {}", id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") String id) {
        log.info("Deleting category for category id {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(null)
                .message(Map.of("success", "Category deleted successfully"))
                .success(true)
                .build();
        categoryService.deleteById(id);
        log.info("Deleted category for category id {}", id);
        return ResponseEntity.ok().body(apiResponse);
    }
}
