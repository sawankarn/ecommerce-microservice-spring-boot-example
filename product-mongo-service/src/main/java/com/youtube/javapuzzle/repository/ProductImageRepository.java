package com.youtube.javapuzzle.repository;

import com.youtube.javapuzzle.model.ProductImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductImageRepository extends MongoRepository<ProductImage, String> {
}
