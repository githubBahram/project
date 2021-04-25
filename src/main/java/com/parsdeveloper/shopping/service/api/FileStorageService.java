package com.parsdeveloper.shopping.service.api;

import com.parsdeveloper.shopping.model.entity.shop.ProductImage;
import org.springframework.core.io.FileSystemResource;

public interface FileStorageService {
    ProductImage save(byte[] bytes, String imageName) throws Exception;

    FileSystemResource find(Long imageId);
}
