package org.example.ite3rdecommerceapplication.repository;

import org.example.ite3rdecommerceapplication.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
