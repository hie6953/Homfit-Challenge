package com.ssafy.homfit.model.service;

import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.homfit.util.MD5Generator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@PropertySource("classpath:cloud.properties")
public class S3Service {

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    private AmazonS3 conn;

    @PostConstruct
    public void S3Util(){
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        
        this.conn = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
    }

    public String uploadImg(MultipartFile file) throws IOException{
        String fileName = null;
        Date now = new Date();
        try {
            String originalFileName = file.getOriginalFilename();
            fileName = new MD5Generator(originalFileName).toString() + now.getTime();
            this.conn.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.conn.getUrl(bucket, fileName).toString();
    }
}
