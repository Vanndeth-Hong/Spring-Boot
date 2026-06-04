package org.example.ite3rdecommerceapplication.repository;

import org.example.ite3rdecommerceapplication.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OderRepository extends JpaRepository<Order, UUID> {

}
