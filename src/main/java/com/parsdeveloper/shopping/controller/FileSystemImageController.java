package com.parsdeveloper.shopping.controller;

import com.parsdeveloper.shopping.service.api.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("image-management")
public class FileSystemImageController {

    @Autowired
    FileStorageService fileStorageService;

    @PostMapping("/image")
    Long uploadImage(@RequestBody MultipartFile image) throws Exception {
        return fileStorageService.save(image.getBytes(), image.getOriginalFilename()).getId();
    }

    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    FileSystemResource downloadImage(@PathVariable Long imageId) throws Exception {
        return fileStorageService.find(imageId);
    }
}
