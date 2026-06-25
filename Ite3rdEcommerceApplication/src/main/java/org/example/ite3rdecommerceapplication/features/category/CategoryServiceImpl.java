package org.example.ite3rdecommerceapplication.features.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ite3rdecommerceapplication.features.category.dto.CategoryResponse;
import org.example.ite3rdecommerceapplication.features.category.dto.CreateCategoryRequest;
import org.example.ite3rdecommerceapplication.features.category.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createNewCategory(CreateCategoryRequest createCategoryRequest) {
        log.info("Create New Category Request: {}", createCategoryRequest);

        boolean isExisting = categoryRepository.existsByName(createCategoryRequest.name());
        if (isExisting) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists");
        }

        Category parentCategory = null;
        if (createCategoryRequest.parentCategoryId() != null) {
            parentCategory = categoryRepository.findById(createCategoryRequest.parentCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parent Category Not Found"));
        }

        Category category = categoryMapper.mapCreateCategoryRequestToCategory(createCategoryRequest);
        category.setIsDeleted(false);
        category.setParentCategory(parentCategory);

        return categoryMapper.mapCategoryToResponse(categoryRepository.save(category));
    }

    @Override
    public Page<CategoryResponse> getAllCategory(int page, int size) {
        log.info("Get all categories by pagination: page={}, size={}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable)
                .map(c -> categoryMapper.mapCategoryToResponse(c));
    }

    @Override
    public CategoryResponse getCategoryById(int id) {
        log.info("Get category by ID: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category ID Not Found"));
        return categoryMapper.mapCategoryToResponse(category);
    }

    @Override
    public List<CategoryResponse> getSubcategoriesByMainCategoryId(int id) {
        log.info("Get subcategories by main category ID: {}", id);
        if (!categoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Main Category Not Found");
        }


        return categoryRepository.findByParentCategoryId(id).stream()
                .map(c -> categoryMapper.mapCategoryToResponse(c))
                .collect(Collectors.toList());
    }

    @Override
    public void hardDeleteCategoryById(int id) {
        log.info("Hard delete category by ID: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category ID Not Found"));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryResponse softDeleteCategoryById(int id) {
        log.info("Soft delete category by ID: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category ID Not Found"));

        category.setIsDeleted(true);
        return categoryMapper.mapCategoryToResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse updateCategoryFieldsById(int id, UpdateCategoryRequest updateCategoryRequest) {
        log.info("Patch update category fields by ID: {}, Data: {}", id, updateCategoryRequest);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category ID Not Found"));

        if (updateCategoryRequest.name() != null) {
            category.setName(updateCategoryRequest.name());
        }
        if (updateCategoryRequest.icon() != null) {
            category.setIcon(updateCategoryRequest.icon());
        }
        if (updateCategoryRequest.description() != null) {
            category.setDescription(updateCategoryRequest.description());
        }

        return categoryMapper.mapCategoryToResponse(categoryRepository.save(category));
    }


}
