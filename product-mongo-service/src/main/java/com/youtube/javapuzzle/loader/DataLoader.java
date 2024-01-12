package com.youtube.javapuzzle.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youtube.javapuzzle.model.Category;
import com.youtube.javapuzzle.model.Product;
import com.youtube.javapuzzle.repository.CategoryRepository;
import com.youtube.javapuzzle.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
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
//        insertProductsData();
    }

    private void insertProductsData(){
        try {
            String[] categoryArr = {"electronics","jewelery","men","clothing","women"};
            // Fetch JSON from the API
            URL url = new URL("https://fakestoreapi.com/products");
            ObjectMapper objectMapper = new ObjectMapper();
            ProductLoader[] productsArray = objectMapper.readValue(url, ProductLoader[].class);

            // Convert array to list
            List<ProductLoader> productList = Arrays.asList(productsArray);

            // Print the products
            for (ProductLoader productLoader : productList) {
                Set<Category> categoryList = getStrings(productLoader);
                Product product = new Product();
                product.setName(productLoader.getTitle());
                product.setDescription(productLoader.getDescription());
                product.setCategories(categoryList);
                product.setPrice(new BigDecimal(productLoader.getPrice()));
                productRepository.save(product);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<Category> getStrings(ProductLoader productLoader) {
        Set<Category> categoryList = new HashSet<>();
        String category = productLoader.getCategory();

        if(category.contains("men") && category.contains("clothing")){
           categoryList.add(getCategoryByName("men"));
           categoryList.add(getCategoryByName("clothing"));
        } else if(category.contains("women") && category.contains("clothing")){
            categoryList.add(getCategoryByName("women"));
            categoryList.add(getCategoryByName("clothing"));
        }else{
            categoryList.add(getCategoryByName(category));
        }
        return categoryList;
    }

    private Category getCategoryByName(String name){
        Optional<Category> catRepo = categoryRepository.findByName(name).stream().findFirst();
        if(catRepo.isPresent()){
            return catRepo.get();
        }
        return categoryRepository.save(Category.builder()
                        .name(name)
                .build());
    }

}
