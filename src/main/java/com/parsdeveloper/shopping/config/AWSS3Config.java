package com.parsdeveloper.shopping.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSS3Config {

	@Value("${aws.access_key_id}")
	private String accessKeyId;
	@Value("${aws.secret_access_key}")
	private String secretAccessKey;
	@Value("${aws.s3.region}")
	private String region;
	@Value("${aws.s3.endpointUrl}")
	private String endpointUrl;

	@Bean
	public AmazonS3 getAmazonS3Client() {
		final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
		return AmazonS3ClientBuilder
				.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpointUrl,region))
				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
				.build();
	}
}
