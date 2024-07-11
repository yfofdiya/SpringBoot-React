package com.practice.blog.util;

import com.practice.blog.dto.UserDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWTApiResponse {
    private String token;
    private UserDto user;
}
