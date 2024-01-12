package com.youtube.javapuzzle.controller;

import com.github.javafaker.Cat;
import com.youtube.javapuzzle.model.Category;
import com.youtube.javapuzzle.model.Product;
import com.youtube.javapuzzle.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.CategoryRequest;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @PostMapping
    public void createCategory(@RequestBody CategoryRequest categoryRequest){
        categoryRepository.save(mapCategoryRequestToCategory(categoryRequest));
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    public Category mapCategoryRequestToCategory(CategoryRequest categoryRequest){
        return Category.builder()
                .name(categoryRequest.getName())
                .build();
    }
}
