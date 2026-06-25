package org.example.ite3rdecommerceapplication.features.product;

import org.example.ite3rdecommerceapplication.features.product.dto.CreateProductRequest;
import org.example.ite3rdecommerceapplication.features.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {
    /**
     * Find products by pagination
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<ProductResponse> findAll(int pageNumber, int pageSize);


    /**
     * Create a new product
     * @param createProductRequest is requesting data for creating product
     * @return {@link ProductResponse}
     * author vanndeth_hong
     */

    ProductResponse createNew(CreateProductRequest createProductRequest);
}
