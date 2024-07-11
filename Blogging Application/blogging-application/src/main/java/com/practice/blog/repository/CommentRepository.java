package com.practice.blog.repository;

import com.practice.blog.entity.Comment;
import com.practice.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByPostOrderByCreatedDateDesc(Post post);
}
