package org.example.ite3rdecommerceapplication.features.product;

import org.example.ite3rdecommerceapplication.features.product.dto.CreateProductRequest;
import org.example.ite3rdecommerceapplication.features.product.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product mapCreateProductRequestToProduct(CreateProductRequest createProductRequest);
    ProductResponse mapProductToProductResponse(Product product);

}
