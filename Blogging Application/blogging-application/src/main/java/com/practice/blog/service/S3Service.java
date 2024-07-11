package com.practice.blog.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    String uploadToS3(MultipartFile file);
    byte[] downloadFromS3(String fileName);
    String deleteFromS3(String fileName);
}
