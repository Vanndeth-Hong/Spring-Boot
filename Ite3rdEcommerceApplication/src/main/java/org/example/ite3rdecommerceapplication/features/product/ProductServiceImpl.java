package org.example.ite3rdecommerceapplication.features.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ite3rdecommerceapplication.features.category.Category;
import org.example.ite3rdecommerceapplication.features.category.CategoryMapper;
import org.example.ite3rdecommerceapplication.features.category.CategoryRepository;
import org.example.ite3rdecommerceapplication.features.product.dto.CreateProductRequest;
import org.example.ite3rdecommerceapplication.features.product.dto.ProductResponse;
import org.example.ite3rdecommerceapplication.utils.GenerateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<ProductResponse> findAll(int pageNumber, int pageSize){
        Sort sortById =Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        Page<Product> productPage = productRepository.findAll(pageRequest);
        return productPage.map(productMapper::mapProductToProductResponse);
    }

    @Override
    public ProductResponse createNew(CreateProductRequest createProductRequest) {

        // Validate product name
        if (productRepository.existsByName(createProductRequest.name())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Product name has already exists");
        }

        // Validate Category
        Category category = categoryRepository
                .findById(createProductRequest.categoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        // Transfer data from DTO to Model
        Product product = productMapper
                .mapCreateProductRequestToProduct(createProductRequest);

        product.setCategory(category);
        product.setCode(GenerateUtils.generateProductCode());
        product.setSlug(GenerateUtils.generateSlug(createProductRequest.name()));
        product.setIsAvailable(true);
        product.setIsDeleted(false);

        product = productRepository.save(product);

        return productMapper.mapProductToProductResponse(product);

    }
}
