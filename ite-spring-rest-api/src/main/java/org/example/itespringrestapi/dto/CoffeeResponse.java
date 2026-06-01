package org.example.itespringrestapi.dto;

import java.math.BigDecimal;

public record CoffeeResponse(
        String name,
        String description,
        BigDecimal price
) {
}
