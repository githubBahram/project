package com.parsdeveloper.shopping.service.impl;

import com.parsdeveloper.shopping.service.api.FileStorageService;
import org.springframework.stereotype.Service;

@Service
public class DefaultFileLocationService implements FileStorageService {
//    @Autowired
//    FileStorageRepository fileSystemRepository;
//
//    @Autowired
//    ImageRepository imageRepository;
//
//    public ProductImage save(byte[] bytes, String imageName) throws IOException {
//        String location = fileSystemRepository.save(bytes, imageName);
//
//        return imageRepository.save(new ProductImage());
//    }
//
//    public FileSystemResource find(Long imageId) {
//        ProductImage image = imageRepository.findById(imageId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        return fileSystemRepository.findInFileSystem(image.getLocation());
//    }

}
