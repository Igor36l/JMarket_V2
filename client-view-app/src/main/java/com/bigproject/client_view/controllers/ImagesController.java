package com.bigproject.client_view.controllers;

import com.bigproject.client_view.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products/images")
@EnableAsync
public class ImagesController {

    private final ImageService imageService;

    List<String> imageNameList = new ArrayList<>();
    List<String> imagePathList = new ArrayList<>();

    @PostMapping
    public CompletableFuture<String>  uploadImage(@RequestParam MultipartFile file, Model model){

        try {
            if(file.isEmpty()){
                throw new RuntimeException("Вы ничего не загрузили");
            }
            CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
               String s = imageService.saveImage(file);
                imagePathList.add(s);
                imageNameList.add(file.getOriginalFilename());
                model.addAttribute("productImages", imageNameList);
                model.addAttribute("imagePath", imagePathList);
            });
             return imageFuture.thenApplyAsync(result -> "catalogue/products/product_image"
            );

        }catch (RuntimeException exception){
            CompletableFuture<Void> imageExceptionFuture = CompletableFuture.runAsync(() -> {
                model.addAttribute("errors", exception.getMessage());
            });
            return imageExceptionFuture.thenApplyAsync(result -> {
                model.addAttribute("errors", exception.getMessage());
                return "catalogue/products/product_image";
            });
        }

    }
}
