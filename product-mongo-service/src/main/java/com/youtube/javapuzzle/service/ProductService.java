package com.youtube.javapuzzle.service;

import com.youtube.javapuzzle.model.Product;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import product.ProductRequest;
import product.ProductResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<Product> getAllProducts();

    Product getProductById(String productId);

    List<Product> getProductsByName(String name);

    Product updateProduct(String productId, Product product) throws Exception;

    void deleteProduct(String productId);

    List<Product> findProductsByCategoryName(String categoryName);

    List<Product> getProductsByFullTextSearch(String name);

    List<Product> getProductsAbovePrice(BigDecimal price);
}
