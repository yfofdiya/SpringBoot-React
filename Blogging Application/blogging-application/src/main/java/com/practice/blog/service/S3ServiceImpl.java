package com.practice.blog.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class S3ServiceImpl implements S3Service {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3;

    public String uploadToS3(MultipartFile file) {
        String[] result = removeExtension(Objects.requireNonNull(file.getOriginalFilename()));
        String name = result[0];
        String extension = result[1];
        String fileName = name + "_" + System.currentTimeMillis() + "." + extension;
        File fileObject = convertMultiPartFileToFile(file);
        s3.putObject(new PutObjectRequest(bucketName, fileName, fileObject));
        fileObject.delete();
        return fileName;
    }

    public byte[] downloadFromS3(String fileName) {
        S3Object object = s3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = object.getObjectContent();
        byte[] content = null;
        try {
            content = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            log.error("Error converting multipart to file", e);
        }
        return content;
    }

    public String deleteFromS3(String fileName) {
        s3.deleteObject(bucketName, fileName);
        return "File " + fileName + " deleted successfully";
    }

    private String[] removeExtension(String fileName) {
        String[] result = new String[2];
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            result[0] = fileName;
            result[1] = "";
        } else {
            result[0] = fileName.substring(0, lastDotIndex);
            result[1] = fileName.substring(lastDotIndex + 1);
        }
        return result;
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipart to file", e);
        }
        return convertedFile;
    }
}
