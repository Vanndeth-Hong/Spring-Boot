package org.example.ite3rdecommerceapplication.features.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);

    List<Category> findByParentCategoryId(Integer parentCategoryId);


}
