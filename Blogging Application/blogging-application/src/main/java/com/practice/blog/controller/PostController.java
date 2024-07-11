package com.practice.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.blog.dto.PostDto;
import com.practice.blog.service.FileService;
import com.practice.blog.service.PostService;
import com.practice.blog.util.ApiResponse;
import com.practice.blog.util.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "Post Controller", description = "CRUD operation for post")
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<ApiResponse> createPost(
            @RequestPart("postData") String postData,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        log.info("Post creation started...");
        PostDto postDto = mapper.readValue(postData, PostDto.class);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(postService.createPost(postDto, image))
                .message(Map.of("success", "Post created successfully"))
                .success(true)
                .build();
        log.info("Post creation completed...");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "direction", defaultValue = AppConstants.DIRECTION, required = false) String direction
    ) {
        log.info("Fetching all posts for {} page", pageNumber + 1);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(postService.getAllPosts(pageNumber, pageSize, sortBy, direction))
                .message(Map.of("success", "All posts fetched"))
                .build();
        log.info("Fetched all posts for page {}", pageNumber + 1);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPostById(@PathVariable("id") String id) {
        log.info("Fetching post for post id {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(postService.getPostById(id))
                .message(Map.of("success", "Post fetched for id " + id))
                .build();
        log.info("Fetched post for post id {}", id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable("id") String id) {
        log.info("Deleting post for post id {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(null)
                .message(Map.of("success", "Post deleted successfully"))
                .success(true)
                .build();
        postService.deleteById(id);
        log.info("Deleted post for post id {}", id);
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse> getAllPostsByCategoryId(
            @PathVariable("id") String id,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "direction", defaultValue = AppConstants.DIRECTION, required = false) String direction) {
        log.info("Fetching all posts for category id {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(postService.getAllPostsByCategory(id, pageNumber, pageSize, sortBy, direction))
                .message(Map.of("success", "All posts fetched for category with id " + id))
                .success(true)
                .build();
        log.info("Fetched all posts for category id {}", id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse> getAllPostsByUserId(@PathVariable("id") String id) {
        log.info("Fetching all posts for user id {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(postService.getAllPostsByUser(id))
                .message(Map.of("success", "All posts fetched for user with id " + id))
                .success(true)
                .build();
        log.info("Fetched all posts for user id {}", id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePost(@PathVariable("id") String id, @RequestBody PostDto postDto) {
        log.info("Updating post for post it {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(postService.updatePost(id, postDto))
                .message(Map.of("success", "Post updated successfully"))
                .success(true)
                .build();
        log.info("Updated post for post it {}", id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<ApiResponse> searchPostsContainingKeyword(@PathVariable("keyword") String keyword) {
        log.info("Searching post with {}", keyword);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(postService.searchPostsContainingKeyword(keyword))
                .message(Map.of("success", "All posts fetched containing keyword " + keyword + " in the title field"))
                .success(true)
                .build();
        log.info("Got post with {}", keyword);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/upload/image/{id}")
    public ResponseEntity<ApiResponse> uploadPostImage(@PathVariable("id") String id, @RequestParam(value = "image") MultipartFile image) throws IOException {
        PostDto postDto = postService.getPostById(id);

        log.info("Image uploading started...");
        String imageName = fileService.uploadImage(image);
        log.info("Image uploading completed...");
        log.info("Uploaded Image {}", imageName);

        postDto.setImage(imageName);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(postService.updatePost(id, postDto))
                .message(Map.of("success", "File uploaded successfully"))
                .success(true).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/download/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ApiResponse> downloadPostImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        log.info("Downloading image");
        InputStream resource = fileService.getImage(imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
        log.info("Image downloaded");

        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message(Map.of("success", "Post image downloaded successfully"))
                .success(true)
                .build(), HttpStatus.OK);
    }
}
