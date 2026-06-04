package org.example.ite3rdecommerceapplication.repository;

import org.example.ite3rdecommerceapplication.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
