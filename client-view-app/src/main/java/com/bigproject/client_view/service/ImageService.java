package com.bigproject.client_view.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${file.path}") private String filePath;

    public String saveImage(MultipartFile file){
        try {
            String fileName = UUID.randomUUID() + "." + file.getOriginalFilename() ;
            file.transferTo(new File(filePath + "/" + fileName));
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
