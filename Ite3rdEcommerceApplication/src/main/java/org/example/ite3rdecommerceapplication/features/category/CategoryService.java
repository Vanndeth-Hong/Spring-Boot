package org.example.ite3rdecommerceapplication.features.category;

import org.example.ite3rdecommerceapplication.features.category.dto.CategoryResponse;
import org.example.ite3rdecommerceapplication.features.category.dto.CreateCategoryRequest;
import org.example.ite3rdecommerceapplication.features.category.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {


    CategoryResponse createNewCategory(CreateCategoryRequest createCategoryRequest);


    Page<CategoryResponse> getAllCategory(int page, int size);


    CategoryResponse getCategoryById(int id);


    List<CategoryResponse> getSubcategoriesByMainCategoryId(int id);


    void hardDeleteCategoryById(int id);


    CategoryResponse softDeleteCategoryById(int id);


    CategoryResponse updateCategoryFieldsById(int id, UpdateCategoryRequest updateCategoryRequest);
}
