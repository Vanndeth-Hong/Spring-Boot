package org.example.ite3rdecommerceapplication.features.product.dto;

import jakarta.validation.constraints.*;
import org.example.ite3rdecommerceapplication.features.category.dto.CategorySnippetResponse;

import java.math.BigDecimal;

public record CreateProductRequest(

        @NotBlank(message = "Name is required")
        @Size(max = 255)
        String name,
        @Size(max = 255)
        String description,
        @Size(max = 255)
        String thumbnail,
        @NotNull(message = "Unit price is required")
        @Min(0)
        BigDecimal unitPrice,
        @NotNull(message = "QTY is required")
        @Min(0)
        Integer qty,
        @NotNull(message = "Category ID is required")
        @Positive
        Integer categoryId
) {
}
