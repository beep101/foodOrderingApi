package com.example.demo.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.example.demo.service_interfaces.ImageStorageServiceInterface;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;

@Service
public class ImageStorageService implements ImageStorageServiceInterface{
	
	private AmazonS3 s3;
	private String bucketName="donesi.projekat";
	
	//aws keys for s3bucket
	public ImageStorageService() {
		s3=AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(new AWSCredentials() {
			
			@Override
			public String getAWSSecretKey() {
				return "***";
			}
			
			@Override
			public String getAWSAccessKeyId() {
				return "***";
			}
		})).withRegion(Regions.EU_CENTRAL_1).build();
	}

	@Override
	public boolean addImage(String type, int id,byte[] image) {
		String key=type+"/"+Integer.toString(id)+".jpg";
		
		InputStream input=new ByteArrayInputStream(image);
		
		ObjectMetadata metadata=new ObjectMetadata();
		metadata.setContentLength(image.length);
		metadata.setContentType("image/jpg");
		s3.putObject(new PutObjectRequest(bucketName, key, input, metadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		return true;
	}

	@Override
	public boolean deleteImage(String type, int id) {
		String key=type+"/"+Integer.toString(id)+".jpg";
		s3.deleteObject(bucketName, key);
		return true;
	}
	
	

}
