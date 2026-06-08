package org.example.ite3rdecommerceapplication.repository;

import org.example.ite3rdecommerceapplication.domain.Category;
import org.example.ite3rdecommerceapplication.dto.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);

    List<Category> findByParentCategoryId(Integer parentCategoryId);


}
