package com.practice.blog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String id;
    private String title;
    private String content;
    private String categoryId;
    private String userId;
    private String image;
    private String createdDate;
    private UserDto userDto;
    private CategoryDto categoryDto;
}
