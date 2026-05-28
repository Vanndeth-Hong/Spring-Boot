package org.example.itespringrestapi.service;

import org.example.itespringrestapi.domain.Coffee;
import org.example.itespringrestapi.dto.CoffeeResponse;

import java.util.List;

public interface CoffeeService {

    List<Coffee> getCoffee();

    CoffeeResponse getCoffeeById(Integer id);

    List<CoffeeResponse> searchByNameAndPrice(String name, Double price);

}
