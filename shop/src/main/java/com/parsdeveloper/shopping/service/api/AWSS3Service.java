package com.parsdeveloper.shopping.service.api;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface AWSS3Service {

    String uploadFile(MultipartFile multipartFile);

    byte[] downloadFile(String keyName);

    MultipartFile createThumbnail(File file, Integer width);

}
