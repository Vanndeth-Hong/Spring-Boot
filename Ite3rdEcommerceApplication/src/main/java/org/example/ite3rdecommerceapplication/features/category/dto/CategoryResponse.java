package org.example.ite3rdecommerceapplication.features.category.dto;

import lombok.Builder;

@Builder
public record CategoryResponse(
        Integer id,
        String name,
        String description,
        Boolean isDeleted,
        Integer parentCategoryId
) {
}
