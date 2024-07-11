package com.practice.blog.repository;

import com.practice.blog.entity.Category;
import com.practice.blog.entity.Post;
import com.practice.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {
    Page<Post> findByCategory(Category category, Pageable pageable);
    List<Post> findByUser(User user);
    List<Post> findByTitleContaining(String title);
}
