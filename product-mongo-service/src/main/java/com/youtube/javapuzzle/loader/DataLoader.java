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
import java.util.*;

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
        List<String> categoriesList = new ArrayList<>(Arrays.asList("mobile","computer","laptop","beauty-products","clothing","shoes","gardening"));
        for(String category: categoriesList){
            categoryRepository.save(Category.builder()
                            .name(category)
                    .build());
        }
        for (int i = 0; i < 20; i++) {
            // Insert categories
            int numberOfCategory  = new Random().nextInt(4)+1;
            Set<Category> categories = new HashSet<>();
            for (int j = 0; j < numberOfCategory; j++) {
                Category category = categoryRepository.findByName(categoriesList.get(j)).get();
                categories.add(category);
            }

            Product product = new Product();
            product.setName(faker.commerce().productName());
            product.setDescription(faker.lorem().sentence());
            product.setCategories(categories);
            product.setPrice(new BigDecimal(faker.commerce().price()));
            productRepository.save(product);
        }
    }
}
