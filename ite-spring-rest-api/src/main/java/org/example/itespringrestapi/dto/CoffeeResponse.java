package org.example.itespringrestapi.dto;

public record CoffeeResponse(
        String name,
        String description,
        Double price
) {
}
