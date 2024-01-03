package com.youtube.javapuzzle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "photo")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProductImage {
    private String productId;
    private String imageDescription;
    private Binary productImage;
}
