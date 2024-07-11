package com.practice.blog.service;

import com.practice.blog.dto.CategoryDto;
import com.practice.blog.dto.CommentDto;
import com.practice.blog.dto.PostDto;
import com.practice.blog.dto.UserDto;
import com.practice.blog.entity.Category;
import com.practice.blog.entity.Comment;
import com.practice.blog.entity.Post;
import com.practice.blog.entity.User;
import com.practice.blog.exception.ResourceNotFoundException;
import com.practice.blog.repository.CommentRepository;
import com.practice.blog.repository.PostRepository;
import com.practice.blog.repository.UserRepository;
import com.practice.blog.util.Converter;
import com.practice.blog.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Converter<CommentDto, Comment> commentConverter = new Converter<>(CommentDto.class, Comment.class);
    private static final Converter<PostDto, Post> postConverter = new Converter<>(PostDto.class, Post.class);
    private static final Converter<UserDto, User> userConverter = new Converter<>(UserDto.class, User.class);
    private static final Converter<CategoryDto, Category> categoryConverter = new Converter<>(CategoryDto.class, Category.class);

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = commentConverter.convertToEntity(commentDto);
        comment.setId(UUID.randomUUID().toString());
        comment.setCreatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));
        User user = getUser(commentDto.getUserId());
        comment.setUser(user);
        Post post = getPost(commentDto.getPostId());
        comment.setPost(post);
        PostDto postDto = postConverter.convertToDto(post);
        postDto.setUserDto(userConverter.convertToDto(user));
        postDto.setCategoryDto(categoryConverter.convertToDto(post.getCategory()));
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = commentConverter.convertToDto(savedComment);
        dto.setPostDto(postDto);
        return dto;
    }

    @Override
    public CommentDto updateComment(String id, CommentDto commentDto) {
        Comment comment = getComment(id);
        comment.setContent(commentDto.getContent());
        Comment updatedComment = commentRepository.save(comment);
        CommentDto dto = commentConverter.convertToDto(updatedComment);
        setPostAndConvertEntityToDTO(dto);
        return dto;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentDTOs = getConvertedDTOsList(comments);
        return commentDTOs.stream().peek(this::setPostAndConvertEntityToDTO).toList();
    }

    @Override
    public CommentDto getCommentById(String id) {
        Comment comment = getComment(id);
        CommentDto commentDto = commentConverter.convertToDto(comment);
        setPostAndConvertEntityToDTO(commentDto);
        return commentDto;
    }

    @Override
    public void deleteById(String id) {
        Comment comment = getComment(id);
        commentRepository.delete(comment);
    }

    @Override
    public List<CommentDto> getAllCommentsByPost(String postId) {
        Post post = getPost(postId);
        List<Comment> allCommentsByPost = commentRepository.findByPostOrderByCreatedDateDesc(post);
        List<CommentDto> commentDTOs = getConvertedDTOsList(allCommentsByPost);
        return commentDTOs.stream().peek(this::setPostAndConvertEntityToDTO).toList();
    }

    private User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id", userId));
    }

    private Post getPost(String postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", " Id", postId));
    }

    private Comment getComment(String commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", " Id", commentId));
    }

    private List<CommentDto> getConvertedDTOsList(List<Comment> list) {
        return list.stream().map(commentConverter::convertToDto).toList();
    }

    private void setPostAndConvertEntityToDTO(CommentDto commentDto) {
        Post post = getPost(commentDto.getPostId());
        User user = getUser(commentDto.getUserId());
        PostDto postDto = postConverter.convertToDto(post);
        postDto.setUserDto(userConverter.convertToDto(user));
        postDto.setCategoryDto(categoryConverter.convertToDto(post.getCategory()));
        commentDto.setPostDto(postDto);
    }
}
