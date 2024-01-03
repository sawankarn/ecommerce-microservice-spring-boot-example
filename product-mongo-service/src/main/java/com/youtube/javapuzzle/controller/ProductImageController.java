package com.youtube.javapuzzle.controller;

import com.youtube.javapuzzle.model.ProductImage;
import com.youtube.javapuzzle.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/productImage")
public class ProductImageController {
    @Autowired
    private ProductImageService photoService;

    @PostMapping("/{productId}")
    public String addPhoto(@PathVariable("productId") String productId, @RequestParam("image") MultipartFile image) throws IOException {
        return photoService.addProductImage(productId,
                "", image.getOriginalFilename(), image);
    }

}
