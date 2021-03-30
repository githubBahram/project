package com.parsdeveloper.shopping.service;

import com.parsdeveloper.shopping.model.entity.Image;
import com.parsdeveloper.shopping.repository.FileStorageRepository;
import com.parsdeveloper.shopping.repository.FileSystemRepository;
import com.parsdeveloper.shopping.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class FileLocationService implements FileStorageService {
    @Autowired
    FileStorageRepository fileSystemRepository;

    @Autowired
    ImageRepository imageRepository;

    public Image save(byte[] bytes, String imageName) throws IOException {
        String location = fileSystemRepository.save(bytes, imageName);

        return imageRepository.save(new Image(location, imageName));
    }

    public FileSystemResource find(Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.findInFileSystem(image.getLocation());
    }

}
