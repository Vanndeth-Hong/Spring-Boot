package org.example.ite3rdecommerceapplication.features.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ite3rdecommerceapplication.features.product.dto.CreateProductRequest;
import org.example.ite3rdecommerceapplication.features.product.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/products")
public class ProductController {
    private final ProductService productService;

    public Page<ProductResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize)
    {
        return productService.findAll(pageNumber, pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse create(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return productService.createNew(createProductRequest);
    }
}
