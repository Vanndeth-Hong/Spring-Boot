package org.example.ite3rdecommerceapplication.repository;

import org.example.ite3rdecommerceapplication.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
