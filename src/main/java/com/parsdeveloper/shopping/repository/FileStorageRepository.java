package com.parsdeveloper.shopping.repository;

import org.springframework.core.io.FileSystemResource;

import java.io.IOException;

public interface FileStorageRepository {
    String save(byte[] content, String imageName) throws IOException;

    FileSystemResource findInFileSystem(String location);
}
