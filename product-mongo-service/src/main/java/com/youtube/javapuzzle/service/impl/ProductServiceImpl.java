package com.youtube.javapuzzle.service.impl;

import com.youtube.javapuzzle.mapper.ProductMapper;
import com.youtube.javapuzzle.model.Category;
import com.youtube.javapuzzle.model.Product;
import com.youtube.javapuzzle.repository.CategoryRepository;
import com.youtube.javapuzzle.repository.ProductRepository;
import com.youtube.javapuzzle.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import product.ProductRequest;
import product.ProductResponse;
import product.exception.ProductNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
//        Set<Category> categories = new HashSet<>();
//        Set<CategoryRequest> categoryRequests = productRequest.getCategories();
//
//        for (CategoryRequest categoryRequest : categoryRequests) {
//            Optional<Category> categoryOptional = categoryRepository.findByName(categoryRequest.getName());
//
//            if (categoryOptional.isPresent()) {
//                categories.add(categoryOptional.get());
//            } else {
//                Category newCategory = new Category();
//                newCategory.setName(categoryRequest.getName());
//                Category savedCategory = categoryRepository.save(newCategory);
//                categories.add(savedCategory);
//            }
//        }
        Set<Category> categories = productRequest.getCategories().stream()
                .map(categoryRequest ->
                        categoryRepository.findByName(categoryRequest.getName())
                                .orElseGet(() -> {
                                    Category newCategory = Category.builder()
                                            .name(categoryRequest.getName())
                                            .build();
                                    return categoryRepository.save(newCategory);
                                })
                )
                .collect(Collectors.toSet());
        Product product = productMapper.productRequestToProduct(productRequest);
        product.setCategories(categories);

        Product createdProduct = productRepository.save(product);
        return productMapper.productToProductResponse(createdProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product updateProduct(String productId, Product updatedProduct) throws Exception {
        // Find the existing product by ID
        Optional<Product> existingProductOptional = productRepository.findById(productId);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();

            // Update fields based on your requirement
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());

            // Update categories
            Set<Category> updatedCategories = updatedProduct.getCategories().stream()
                    .map(categoryRequest ->
                            categoryRepository.findByName(categoryRequest.getName())
                                    .orElseGet(() -> {
                                        Category newCategory = new Category();
                                        newCategory.setName(categoryRequest.getName());
                                        return categoryRepository.save(newCategory);
                                    })
                    )
                    .collect(Collectors.toSet());

            existingProduct.setCategories(updatedCategories);


            return productRepository.save(existingProduct);
        } else {
            // Handle the case where the product with the given ID is not found
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }
}
