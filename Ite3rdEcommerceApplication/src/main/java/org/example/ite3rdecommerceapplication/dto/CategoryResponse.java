package org.example.ite3rdecommerceapplication.dto;

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
