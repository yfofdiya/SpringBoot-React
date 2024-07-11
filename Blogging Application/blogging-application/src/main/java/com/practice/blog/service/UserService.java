package com.practice.blog.service;

import com.practice.blog.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String id, UserDto userDto);

    UserDto getUserById(String id);

    List<UserDto> getAllUsers();

    void deleteUserById(String id);

    UserDto getUserByUsername(String username);
}
