package com.parsdeveloper.shopping.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.parsdeveloper.shopping.service.api.AWSS3Service;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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

    /**
     * @param multipartFile upload file as multipartFile
     * @return name file as String
     * @Async annotation ensures that the method is executed in a different background thread
     * but not consume the main thread.
     * after upload To remove the file locally created in the project folder.
     */
    @Override
    @Async
    public String uploadFile(final MultipartFile multipartFile,String bucketName) {
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

    @Override
    @Async
    public String uploadFile(MultipartFile multipartFile, String bucketName,String fileName){
        LOGGER.info("File upload in progress.");
        try {
            final File file = convertMultiPartFileToFile(multipartFile,fileName);
            fileName = uploadFileToS3Bucket(bucketName, file);
            LOGGER.info("File upload is completed.");
            file.delete();
        } catch (final AmazonServiceException ex) {
            LOGGER.info("File upload is failed.");
            LOGGER.error("Error= {} while uploading file.", ex.getMessage());
        }
        return fileName;
    }

    @Override
    @Async
    public String uploadFileByRandomName(final MultipartFile multipartFile,String bucketName) {
        LOGGER.info("File upload in progress.");
        String fileName = multipartFile.getName();
        try {
            final File file = convertMultiPartFileToRandomFile(multipartFile);
            fileName = uploadFileToS3Bucket(bucketName, file);
            LOGGER.info("File upload is completed.");
            file.delete();
        } catch (final AmazonServiceException ex) {
            LOGGER.info("File upload is failed.");
            LOGGER.error("Error= {} while uploading file.", ex.getMessage());
        }
        return fileName;
    }

    private File convertMultiPartFileToRandomFile(final MultipartFile multipartFile) {
        String uuid = UUID.randomUUID().toString();
        final File file = new File(uuid.split("-")[4] + multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
        }
        return file;
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
        }
        return file;
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile,String fileName) {
        final File file = new File(fileName);
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
        }
        return file;
    }

    private String uploadFileToS3Bucket(final String bucketName, final File file) {
        final String uniqueFileName = file.getName();
        LOGGER.info("Uploading file with name= " + uniqueFileName);
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);
        return uniqueFileName;

        /*PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, keyName, image);
       AccessControlList acl = new AccessControlList();
       acl.grantPermission(GroupGrantee.AllUsers, Permission.Read); //all users or authenticated
       putObjectRequest.setAccessControlList(acl);
       s3client.putObject(putObjectRequest);*/
    }

    /**
     * @param keyName is name file
     * @return file as stream byte
     * @Async annotation ensures that the method is executed in a different background thread
     * but not consume the main thread.
     */
    @Override
    @Async
    public byte[] downloadFile(final String keyName,String bucketName) {

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
