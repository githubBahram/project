package com.parsdeveloper.shopping.controller;

import com.parsdeveloper.shopping.service.api.AWSS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value= "/fileManager")
public class AWSS3Ctrl {

	@Autowired
	private AWSS3Service service;

	@PostMapping(value= "/upload")
	public ResponseEntity<String> uploadFile(@RequestPart(value= "file") final MultipartFile multipartFile) {
		String fileName=service.uploadFile(multipartFile);
		final String response = fileName;
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value= "/download")
	public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam(value= "fileName") final String keyName) {
		final byte[] data = service.downloadFile(keyName);
		final ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity
				.ok()
				.contentLength(data.length)
				.header("Content-type", "application/octet-stream")
				.header("Content-disposition", "attachment; filename=\"" + keyName + "\"")
				.body(resource);
	}
}
