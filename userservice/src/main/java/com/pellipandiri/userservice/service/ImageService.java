package com.pellipandiri.userservice.service;


import com.pellipandiri.userservice.config.S3Configurations;
import com.pellipandiri.userservice.entities.FunctionHalls;
import com.pellipandiri.userservice.entities.FunctionHallsImages;
import com.pellipandiri.userservice.repository.FunctionHallImagesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {

    private final S3Configurations s3Configuration;

    private final S3Client s3Client;

    private final OwnerService ownerService;

    private final FunctionHallImagesRepository functionHallImagesRepository;

    public ImageService(S3Configurations s3Configuration, S3Client s3Client, OwnerService ownerService,
                        FunctionHallImagesRepository functionHallImagesRepository) {
        this.s3Configuration = s3Configuration;
        this.s3Client = s3Client;
        this.ownerService=ownerService;
        this.functionHallImagesRepository=functionHallImagesRepository;
    }

    @Transactional
    public String addImageToFunctionHall(Long functionHallId, MultipartFile file) throws IOException {

        // check whether particular functionHall is presents or not.
        FunctionHalls functionHalls = ownerService.getFunctionHallByIdWithOutDTO(functionHallId);
        StringBuilder finalPathofImage = new StringBuilder();

        finalPathofImage.append("https://");
        finalPathofImage.append(s3Configuration.getBucket());
        finalPathofImage.append(".s3.");
        finalPathofImage.append(s3Client.serviceClientConfiguration().region().id());
        finalPathofImage.append(".amazonaws.com/");
        // need to add the image to s3 and save the path to the db.

        String key = "images/"+functionHallId+ "/" + file.getOriginalFilename();


        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(s3Configuration.getBucket())
                .key(key)
//                .acl(ObjectCannedACL.PUBLIC_READ)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(
                putObjectRequest,
                RequestBody.fromInputStream(file.getInputStream(),file.getSize())
        );
        finalPathofImage.append(key);

        // add the file to Db

        FunctionHallsImages functionHallsImages = new FunctionHallsImages();
        functionHallsImages.setFunctionId(functionHalls);
        functionHallsImages.setImagePath(finalPathofImage.toString());
        functionHallImagesRepository.save(functionHallsImages);

        return finalPathofImage.toString();
    }


    @Transactional
    public List<String> getImagesForFunctionHall(Long functionHallId){

        FunctionHalls functionHalls = ownerService.getFunctionHallByIdWithOutDTO(functionHallId);
        return functionHallImagesRepository.findAllByFunctionId(functionHalls).stream().
                map(FunctionHallsImages::getImagePath).toList();
    }
}
