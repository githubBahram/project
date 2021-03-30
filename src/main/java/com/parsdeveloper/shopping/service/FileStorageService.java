package com.parsdeveloper.shopping.service;

import com.parsdeveloper.shopping.model.entity.Image;
import org.springframework.core.io.FileSystemResource;

public interface FileStorageService {
    Image save(byte[] bytes, String imageName) throws Exception;

    FileSystemResource find(Long imageId);
}
