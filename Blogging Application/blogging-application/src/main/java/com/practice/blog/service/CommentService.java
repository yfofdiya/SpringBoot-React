package com.practice.blog.service;

import com.practice.blog.dto.CommentDto;
import com.practice.blog.entity.User;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto);

    CommentDto updateComment(String id, CommentDto commentDto);

    List<CommentDto> getAllComments();

    CommentDto getCommentById(String id);

    void deleteById(String id);

    List<CommentDto> getAllCommentsByPost(String postId);
}
