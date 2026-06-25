package org.example.ite3rdecommerceapplication.features.product.dto;

import org.example.ite3rdecommerceapplication.features.category.dto.CategorySnippetResponse;

import java.math.BigDecimal;

public record ProductResponse(
        String code,
        String slug,
        String name,
        String description,
        String thumbnail,
        BigDecimal unitPrice,
        Integer qty,
        Boolean isAvailable,
        Boolean isDeleted,
        CategorySnippetResponse category
) {
}
