package com.practice.blog.service;

import com.practice.blog.dto.UserDto;
import com.practice.blog.entity.Role;
import com.practice.blog.entity.User;
import com.practice.blog.exception.ResourceNotFoundException;
import com.practice.blog.repository.UserRepository;
import com.practice.blog.util.AppConstants;
import com.practice.blog.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Converter<UserDto, User> converter = new Converter<>(UserDto.class, User.class);

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setId(UUID.randomUUID().toString());
        User userDtoToUser = converter.convertToEntity(userDto);
        setUserRoles(userDtoToUser, userDto);
        userDtoToUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User createdUser = userRepository.save(userDtoToUser);
        return converter.convertToDto(createdUser);
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " Id", id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAbout(userDto.getAbout());
        User updatedUser = userRepository.save(user);
        return converter.convertToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " Id", id));
        return converter.convertToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(converter::convertToDto).toList();
    }

    @Override
    public void deleteUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " Id", id));
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", " Email", username));
        return converter.convertToDto(user);
    }

    private void setUserRoles(User user, UserDto userDto) {
        String rolesWithCommasAndWhiteSpaces = userDto.getRole();
        List<String> roles = trimSpacesAndConvertToList(rolesWithCommasAndWhiteSpaces);
        for (String s : roles) {
            Role role = new Role();
            if (s.equalsIgnoreCase("admin")) {
                role.setId(AppConstants.ADMIN_ROLE_ID);
                role.setName(AppConstants.ADMIN_ROLE_NAME);
            } else if (s.equalsIgnoreCase("normal")) {
                role.setId(AppConstants.NORMAL_ROLE_ID);
                role.setName(AppConstants.NORMAL_ROLE_NAME);
            } else {
                role.setId(AppConstants.NORMAL_ROLE_ID);
                role.setName(AppConstants.NORMAL_ROLE_NAME);
            }
            user.getRoles().add(role);
        }

    }

    private List<String> trimSpacesAndConvertToList(String rolesWithCommasAndWhiteSpaces) {
        String[] role = rolesWithCommasAndWhiteSpaces.split(",");
        List<String> roles = new ArrayList<>();
        for (String r : role) {
            roles.add(r.trim());
        }
        return roles;
    }
}
