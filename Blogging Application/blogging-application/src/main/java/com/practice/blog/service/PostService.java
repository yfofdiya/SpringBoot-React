package com.practice.blog.service;

import com.practice.blog.dto.PostDto;
import com.practice.blog.util.PostResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, MultipartFile image) throws IOException;

    PostDto updatePost(String id, PostDto postDto);

    PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String direction);

    PostDto getPostById(String id);

    void deleteById(String id);

    // Get All Posts by Category
    PostResponse getAllPostsByCategory(String categoryId, int pageNumber, int pageSize, String sortBy, String direction);

    // Get All posts by User
    List<PostDto> getAllPostsByUser(String userId);

    // Search Post by Keyword
    List<PostDto> searchPostsContainingKeyword(String keyword);
}
