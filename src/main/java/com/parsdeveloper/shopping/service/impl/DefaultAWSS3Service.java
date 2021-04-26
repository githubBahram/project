package com.parsdeveloper.shopping.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.parsdeveloper.shopping.service.api.AWSS3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DefaultAWSS3Service implements AWSS3Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAWSS3Service.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    /**
     * @param multipartFile upload file as multipartFile
     * @return name file as String
     * @Async annotation ensures that the method is executed in a different background thread
     * but not consume the main thread.
     * after upload To remove the file locally created in the project folder.
     */
    @Override
    @Async
    public String uploadFile(final MultipartFile multipartFile) {
        LOGGER.info("File upload in progress.");
        String fileName = multipartFile.getName();
        try {
            final File file = convertMultiPartFileToFile(multipartFile);
            fileName = uploadFileToS3Bucket(bucketName, file);
            LOGGER.info("File upload is completed.");
            file.delete();
        } catch (final AmazonServiceException ex) {
            LOGGER.info("File upload is failed.");
            LOGGER.error("Error= {} while uploading file.", ex.getMessage());
        }
        return fileName;
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        String uuid = UUID.randomUUID().toString();
        final File file = new File(multipartFile.getOriginalFilename() + uuid);
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
        }
        return file;
    }

    private String uploadFileToS3Bucket(final String bucketName, final File file) {
        final String uniqueFileName = LocalDateTime.now() + "_" + file.getName();
        LOGGER.info("Uploading file with name= " + uniqueFileName);
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file);
        amazonS3.putObject(putObjectRequest);
        return uniqueFileName;
    }

    /**
     * @param keyName is name file
     * @return file as stream byte
     * @Async annotation ensures that the method is executed in a different background thread
     * but not consume the main thread.
     */
    @Override
    @Async
    public byte[] downloadFile(final String keyName) {

        byte[] content = null;
        LOGGER.info("Downloading an object with key= " + keyName);
        final S3Object s3Object = amazonS3.getObject(bucketName, keyName);
        final S3ObjectInputStream stream = s3Object.getObjectContent();
        try {
            content = IOUtils.toByteArray(stream);
            LOGGER.info("File downloaded successfully.");
            s3Object.close();
        } catch (final IOException ex) {
            LOGGER.info("IO Error Message= " + ex.getMessage());
        }
        return content;
    }
}
