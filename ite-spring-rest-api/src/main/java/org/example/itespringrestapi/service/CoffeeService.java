package org.example.itespringrestapi.service;

import org.example.itespringrestapi.dto.CoffeeResponse;

import java.util.List;

public interface CoffeeService {

    List<CoffeeResponse> getCoffee();
}
