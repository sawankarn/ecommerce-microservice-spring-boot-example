package com.youtube.javapuzzle.mapper;

import com.youtube.javapuzzle.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import product.ProductRequest;
import product.ProductResponse;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse productToProductResponse(Product product);
    Product productRequestToProduct(ProductRequest productRequest);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "categories", ignore = true)
//    Product updateProductFromRequest(ProductRequest productRequest, Product existingProduct);
}
