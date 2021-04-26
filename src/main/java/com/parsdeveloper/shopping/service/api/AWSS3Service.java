package com.parsdeveloper.shopping.service.api;

import org.springframework.web.multipart.MultipartFile;

public interface AWSS3Service {

	String uploadFile(MultipartFile multipartFile);
	
	byte[] downloadFile(String keyName);
}
