package com.practice.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private String id;

    @NotBlank(message = "Title must not be empty")
    private String categoryTitle;

    @NotBlank(message = "Description must not be empty")
    @Size(min = 1, max = 250, message = "Description should be up to 250 characters")
    private String categoryDescription;
}
