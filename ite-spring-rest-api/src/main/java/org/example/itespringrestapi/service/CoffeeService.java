package org.example.itespringrestapi.service;

import org.example.itespringrestapi.domain.Coffee;
import org.example.itespringrestapi.dto.CoffeeResponse;
import org.example.itespringrestapi.dto.CreateCoffeeRequest;
import org.example.itespringrestapi.dto.UpdateCoffeeRequest;

import java.util.List;

public interface CoffeeService {
    //Delete
    void deleteCoffeeById(Integer id);

    //1. My login is update coffee information by coffee id
    //2. Expected result is CoffeeResponse
    //3. Parameter is UpdateCoffeeRequest
    CoffeeResponse updateCoffeeById(Integer id, UpdateCoffeeRequest updateCoffeeRequest);

    //1. Expected result (return type: void, object, collection, int...)
    //2. Your login: add single coffee
    //3. Parameters are used for add coffee

    CoffeeResponse createCoffee(CreateCoffeeRequest createCoffeeRequest);

    List<Coffee> getCoffee();

    CoffeeResponse getCoffeeById(Integer id);

    List<CoffeeResponse> searchByNameAndPrice(String name, Double price);

}
