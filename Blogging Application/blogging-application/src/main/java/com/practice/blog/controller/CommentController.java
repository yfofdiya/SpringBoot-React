package com.practice.blog.controller;

import com.practice.blog.dto.CommentDto;
import com.practice.blog.service.CommentService;
import com.practice.blog.util.ApiResponse;
import com.practice.blog.util.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@Tag(name = "Comment Controller", description = "CRUD operation for Comment")
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<ApiResponse> createComment(@Valid @RequestBody CommentDto commentDto) {
        log.info("Comment creation started...");
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(commentService.createComment(commentDto))
                .message(Map.of("success", "Comment created successfully"))
                .success(true)
                .build();
        log.info("Comment creation completed...");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllComments() {
        log.info("Fetching all comments");
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(commentService.getAllComments())
                .message(Map.of("success", "All comments fetched"))
                .build();
        log.info("Fetched all comments");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCommentById(@PathVariable("id") String id) {
        log.info("Fetching comment for comment id {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(commentService.getCommentById(id))
                .message(Map.of("success", "Comment fetched for id " + id))
                .build();
        log.info("Fetched comment for comment id {}", id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable("id") String id) {
        log.info("Deleting comment for comment id {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(null)
                .message(Map.of("success", "Comment deleted successfully"))
                .success(true)
                .build();
        commentService.deleteById(id);
        log.info("Deleted comment for comment id {}", id);
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<ApiResponse> getAllCommentsByPostId(
            @PathVariable("id") String id,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize
    ) {
        log.info("Fetching all comments for post id {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(commentService.getAllCommentsByPost(id))
                .message(Map.of("success", "All comments fetched for post with id " + id))
                .success(true)
                .build();
        log.info("Fetched all comments for post id {}", id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateComment(@PathVariable("id") String id, @Valid @RequestBody CommentDto commentDto) {
        log.info("Updating comment for comment id {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(commentService.updateComment(id, commentDto))
                .message(Map.of("success", "Comment updated successfully"))
                .success(true)
                .build();
        log.info("Updated comment for comment id {}", id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
