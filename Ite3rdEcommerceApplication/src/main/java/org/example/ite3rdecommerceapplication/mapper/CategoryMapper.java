package org.example.ite3rdecommerceapplication.mapper;

import org.example.ite3rdecommerceapplication.domain.Category;
import org.example.ite3rdecommerceapplication.dto.CategoryResponse;
import org.example.ite3rdecommerceapplication.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface CategoryMapper {
    // Return type = Target
    // Parameter = Source
    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);
    CategoryResponse mapCategoryToResponse(Category category);
}
