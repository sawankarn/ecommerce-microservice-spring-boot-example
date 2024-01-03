package com.youtube.javapuzzle.model;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Category {
    @Id
    private String id;
    private String name;
}
