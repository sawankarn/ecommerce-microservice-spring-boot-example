package com.youtube.javapuzzle.repository;

import com.youtube.javapuzzle.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByName(String name);
}
