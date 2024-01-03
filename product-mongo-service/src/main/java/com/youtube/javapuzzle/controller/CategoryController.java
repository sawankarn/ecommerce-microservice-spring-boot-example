package com.youtube.javapuzzle.controller;

import com.youtube.javapuzzle.model.Category;
import com.youtube.javapuzzle.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import product.CategoryRequest;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @PostMapping
    public void createCategory(@RequestBody CategoryRequest categoryRequest){
        categoryRepository.save(mapCategoryRequestToCategory(categoryRequest));
    }



    public Category mapCategoryRequestToCategory(CategoryRequest categoryRequest){
        return Category.builder()
                .name(categoryRequest.getName())
                .build();
    }
}
