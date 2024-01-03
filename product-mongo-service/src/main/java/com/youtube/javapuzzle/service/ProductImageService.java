package com.youtube.javapuzzle.service;

import com.youtube.javapuzzle.model.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ProductImageService {
    public String addProductImage(String productId,
                                  String imageDescription,
                                  String originalFilename,
                                  MultipartFile image)  throws IOException;

}
