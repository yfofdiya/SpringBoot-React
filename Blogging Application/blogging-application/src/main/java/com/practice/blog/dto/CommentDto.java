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
public class CommentDto {
    private String id;

    @NotBlank(message = "Content must not be empty")
    private String content;

    private String createdDate;

    @NotBlank(message = "User Id must not be empty")
    private String userId;

    @NotBlank(message = "Post Id must not be empty")
    private String postId;

    private PostDto postDto;
}
