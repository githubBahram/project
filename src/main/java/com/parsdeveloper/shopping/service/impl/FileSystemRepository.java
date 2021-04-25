package com.parsdeveloper.shopping.service.impl;

import com.parsdeveloper.shopping.service.api.FileStorageService;
import org.springframework.stereotype.Repository;

@Repository
public class FileSystemRepository implements FileStorageService {

//    String RESOURCES_DIR = FileSystemRepository.class.getResource("/")
//            .getPath();
//
//    public String save(byte[] content, String imageName) throws IOException {
//        Path newFile = Paths.get(RESOURCES_DIR + new Date().getTime() + "-" + imageName);
//        Files.createDirectories(newFile.getParent());
//
//        Files.write(newFile, content);
//
//        return newFile.toAbsolutePath()
//                .toString();
//    }
//
//    @Override
//    public FileSystemResource find(Long imageId) {
//        return null;
//    }
//
//    public FileSystemResource findInFileSystem(String location) {
//        try {
//            return new FileSystemResource(Paths.get(location));
//        } catch (Exception e) {
//            // Handle access or file not found problems.
//            throw new RuntimeException();
//        }
//    }
}
