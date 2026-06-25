package org.example.ite3rdecommerceapplication.features.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OderRepository extends JpaRepository<Order, UUID> {

}
