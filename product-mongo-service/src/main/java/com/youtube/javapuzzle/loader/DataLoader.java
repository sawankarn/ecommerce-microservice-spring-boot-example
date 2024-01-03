package com.youtube.javapuzzle.loader;

import com.github.javafaker.Faker;
import com.youtube.javapuzzle.model.Category;
import com.youtube.javapuzzle.model.Product;
import com.youtube.javapuzzle.repository.CategoryRepository;
import com.youtube.javapuzzle.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(ApplicationArguments args) {
        // Insert 20 products with realistic data using JavaFaker
        insertRealisticData();
    }

    private void insertRealisticData() {
        Faker faker = new Faker();

        for (int i = 0; i < 20; i++) {
            // Insert categories
            Category category = new Category();
            category.setName(faker.commerce().department());
            categoryRepository.save(category);

            // Insert products
            Set<Category> categories = new HashSet<>();
            categories.add(category);

            Product product = new Product();
            product.setName(faker.commerce().productName());
            product.setDescription(faker.lorem().sentence());
            product.setCategories(categories);
            product.setPrice(new BigDecimal(faker.commerce().price()));
            productRepository.save(product);
        }
    }
}
