package com.parsdeveloper.shopping.service.api;

import com.parsdeveloper.shopping.model.entity.cor.ProductImage;
import org.springframework.core.io.FileSystemResource;

public interface FileStorageService {
    ProductImage save(byte[] bytes, String imageName) throws Exception;

    FileSystemResource find(Long imageId);
}
