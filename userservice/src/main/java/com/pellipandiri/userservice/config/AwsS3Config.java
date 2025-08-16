package com.pellipandiri.userservice.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;


@Configuration
public class AwsS3Config {


    private final S3Configurations s3Configurations;

    public AwsS3Config(S3Configurations s3Configurations) {
        this.s3Configurations = s3Configurations;
    }


    @Bean
    public S3Client getS3Client(){

        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(
                s3Configurations.getAccesskey(),
                s3Configurations.getSecretkey());
        return S3Client.builder()
                .region(Region.AP_SOUTH_1)
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true) // fixes dotted-bucket names
                        .build())
                .build();



    }


}
