package com.parsdeveloper.shopping.service.api;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface AWSS3Service {

    String uploadFile(MultipartFile multipartFile, String bucketName);

    String uploadFile(MultipartFile multipartFile, String bucketName,String fileName);

    String uploadFileByRandomName(final MultipartFile multipartFile, String bucketName);

    byte[] downloadFile(String keyName, String bucketName);

}
