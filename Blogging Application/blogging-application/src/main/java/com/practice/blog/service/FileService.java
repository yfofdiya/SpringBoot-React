package com.practice.blog.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String uploadImage(MultipartFile file) throws IOException;
    InputStream getImage(String fileName) throws FileNotFoundException;
}
