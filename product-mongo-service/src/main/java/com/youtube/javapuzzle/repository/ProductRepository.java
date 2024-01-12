package com.youtube.javapuzzle.repository;

import com.youtube.javapuzzle.model.Category;
import com.youtube.javapuzzle.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import product.ProductResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByName(String name);
    List<Product> findByCategoriesContains(Category category);
    @Query("{ 'name': { '$regex': ?0, '$options': 'i' } }")
    List<Product> findByNameRegex(String name);
    @Query("{'price': { '$gt': ?0 } }")
    List<Product> findByPriceGreaterThan(BigDecimal minPrice);

}
