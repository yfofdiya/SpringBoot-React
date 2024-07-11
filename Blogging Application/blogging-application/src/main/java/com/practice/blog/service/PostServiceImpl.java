package com.practice.blog.service;

import com.practice.blog.dto.CategoryDto;
import com.practice.blog.dto.PostDto;
import com.practice.blog.dto.UserDto;
import com.practice.blog.entity.Category;
import com.practice.blog.entity.Post;
import com.practice.blog.entity.User;
import com.practice.blog.exception.ResourceNotFoundException;
import com.practice.blog.repository.CategoryRepository;
import com.practice.blog.repository.PostRepository;
import com.practice.blog.repository.UserRepository;
import com.practice.blog.util.Converter;
import com.practice.blog.util.DateUtils;
import com.practice.blog.util.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private S3Service s3Service;

    @Value("${project.image}")
    private String path;

    private static final Converter<PostDto, Post> postConverter = new Converter<>(PostDto.class, Post.class);
    private static final Converter<UserDto, User> userConverter = new Converter<>(UserDto.class, User.class);
    private static final Converter<CategoryDto, Category> categoryConverter = new Converter<>(CategoryDto.class, Category.class);


    @Override
    public PostDto createPost(PostDto postDto, MultipartFile image) throws IOException {
        // Convert DTO to Entity
        Post post = postConverter.convertToEntity(postDto);

        // Set ID, Image and Date
        post.setId(UUID.randomUUID().toString());

        if (image != null) {
//            String imageName = s3Service.uploadToS3(image);
            String imageName = fileService.uploadImage(image);
            post.setImage(imageName);
        }

        post.setCreatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));

        // Fetch User and set it
        User user = getUser(postDto.getUserId());
        post.setUser(user);

        // Fetch Category and set it
        Category category = getCategory(postDto.getCategoryId());
        post.setCategory(category);

        // Save all the data to database
        Post savedPost = postRepository.save(post);

        // Convert Entity to DTO
        PostDto dto = postConverter.convertToDto(savedPost);

        // Set DTOs
        dto.setUserDto(userConverter.convertToDto(user));
        dto.setCategoryDto(categoryConverter.convertToDto(category));

        return dto;
    }

    @Override
    public PostDto updatePost(String id, PostDto postDto) {
        // Get Post and set required fields
        Post post = getPost(id);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        PostDto dto = postConverter.convertToDto(updatedPost);

        setUserAndConvertEntityToDTO(dto);
        setCategoryAndConvertEntityToDTO(dto);

        return dto;
    }

    @Override
    public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortDirection, sortBy);

        // Get All Posts
        Page<Post> pagedPost = postRepository.findAll(pageable);

        List<Post> posts = pagedPost.getContent();

        // Convert Entity to DTO
        List<PostDto> postDTOs = getConvertedDTOsList(posts);

        // Traversing to each Post DTO
        List<PostDto> data = postDTOs.stream().peek(postDto -> {
            setUserAndConvertEntityToDTO(postDto);
            setCategoryAndConvertEntityToDTO(postDto);
        }).toList();

        return PostResponse
                .builder()
                .response(data)
                .pageNumber(pagedPost.getNumber())
                .pageSize(pagedPost.getSize())
                .totalElementsOnPage(pagedPost.getNumberOfElements())
                .totalElements(pagedPost.getTotalElements())
                .totalPages(pagedPost.getTotalPages())
                .lastPage(pagedPost.isLast())
                .firstPage(pagedPost.isFirst())
                .build();
    }

    @Override
    public PostDto getPostById(String id) {
        // Get Post
        Post post = getPost(id);

        // Convert Entity to DTO
        PostDto postDto = postConverter.convertToDto(post);

        // Traversing to Post DTO
        setUserAndConvertEntityToDTO(postDto);
        setCategoryAndConvertEntityToDTO(postDto);

        return postDto;
    }

    @Override
    public void deleteById(String id) {
        // Get Post
        Post post = getPost(id);
        s3Service.deleteFromS3(post.getImage());
        postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPostsByCategory(String categoryId, int pageNumber, int pageSize, String sortBy, String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortDirection, sortBy);

        // Get Category
        Category category = getCategory(categoryId);

        // Get All Posts by Category
        Page<Post> page = postRepository.findByCategory(category, pageable);

        List<Post> allPostsByCategory = page.getContent();

        // Convert Entity to DTO
        List<PostDto> postDTOs = getConvertedDTOsList(allPostsByCategory);

        // Traversing to each Post DTO
        List<PostDto> list = postDTOs.stream().peek(postDto -> {
            setUserAndConvertEntityToDTO(postDto);
            postDto.setCategoryDto(categoryConverter.convertToDto(category));
        }).toList();

        return PostResponse
                .builder()
                .response(list)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElementsOnPage(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .lastPage(page.isLast())
                .firstPage(page.isFirst())
                .build();
    }

    @Override
    public List<PostDto> getAllPostsByUser(String userId) {
        // Get User
        User user = getUser(userId);

        // Get All Posts by User
        List<Post> allPostsByUser = postRepository.findByUser(user);

        // Convert Entity to DTO
        List<PostDto> postDTOs = getConvertedDTOsList(allPostsByUser);

        // Traversing to each Post DTO
        return postDTOs.stream().peek(postDto -> {
            postDto.setUserDto(userConverter.convertToDto(user));
            setCategoryAndConvertEntityToDTO(postDto);
        }).toList();
    }

    @Override
    public List<PostDto> searchPostsContainingKeyword(String keyword) {
        List<Post> allPostsContainingKeyword = postRepository.findByTitleContaining(keyword);
        List<PostDto> postDTOs = getConvertedDTOsList(allPostsContainingKeyword);
        return postDTOs.stream().peek(postDto -> {
            setUserAndConvertEntityToDTO(postDto);
            setCategoryAndConvertEntityToDTO(postDto);
        }).toList();
    }

    private User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id", userId));
    }

    private Category getCategory(String categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " Id", categoryId));
    }

    private Post getPost(String id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", " Id", id));
    }

    private List<PostDto> getConvertedDTOsList(List<Post> list) {
        return list.stream().map(postConverter::convertToDto).toList();
    }

    private void setUserAndConvertEntityToDTO(PostDto postDto) {
        // Get User and also convert entity to dto and set it
        User user = getUser(postDto.getUserId());
        postDto.setUserDto(userConverter.convertToDto(user));
    }

    private void setCategoryAndConvertEntityToDTO(PostDto postDto) {
        // Get Category and also convert entity to dto and set it
        Category category = getCategory(postDto.getCategoryId());
        postDto.setCategoryDto(categoryConverter.convertToDto(category));
    }
}
