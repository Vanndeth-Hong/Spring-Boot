package org.example.ite3rdecommerceapplication.features.category;

import org.example.ite3rdecommerceapplication.features.category.dto.CategoryResponse;
import org.example.ite3rdecommerceapplication.features.category.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface CategoryMapper {
    // Return type = Target
    // Parameter = Source
    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);
    CategoryResponse mapCategoryToResponse(Category category);
}
