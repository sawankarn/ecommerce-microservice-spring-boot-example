package com.youtube.javapuzzle.service.impl;

import com.youtube.javapuzzle.model.ProductImage;
import com.youtube.javapuzzle.repository.ProductImageRepository;
import com.youtube.javapuzzle.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;
    @Override
    public String addProductImage(String productId,
                                  String imageDescription,
                                  String originalFilename,
                                  MultipartFile image) throws IOException {
        ProductImage photo = ProductImage.builder()
                .productId(productId)
                .imageDescription(imageDescription)
                .productImage(new Binary(BsonBinarySubType.BINARY,image.getBytes()))
                .build();

        return productImageRepository.save(photo).getProductId();
    }

}
