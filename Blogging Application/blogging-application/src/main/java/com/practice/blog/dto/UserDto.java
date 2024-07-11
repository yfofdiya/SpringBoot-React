package com.practice.blog.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private String id;

    @NotBlank(message = "Name must be not empty or not null or at least have one character")
    private String name;

    @NotBlank(message = "Email must be not empty or not null")
    @Email(message = "Email format is not valid")
    private String email;

    @NotBlank(message = "Password must be not empty or not null")
    @Size(min = 3, max = 10, message = "Password must not be less than 3 characters or not more than 10 characters")
    private String password;

    @NotBlank(message = "About must be not empty or not null or at least have one character")
    private String about;

    @NotBlank(message = "Role must be not empty or not null or only contains ADMIN and NORMAL words")
    private String role;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getRole() {
        return role;
    }

    @JsonProperty
    public void setRole(String role) {
        this.role = role;
    }
}
