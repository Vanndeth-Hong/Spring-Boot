package org.example.itespringrestapi.service.impl;

import org.example.itespringrestapi.domain.Coffee;
import org.example.itespringrestapi.dto.CoffeeResponse;
import org.example.itespringrestapi.repository.CoffeeRepository;
import org.example.itespringrestapi.service.CoffeeService;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoffeeServiceImpl implements CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public CoffeeServiceImpl ( CoffeeRepository coffeeRepository){
        this.coffeeRepository= coffeeRepository;
    }

    @Override
    public List<CoffeeResponse> getCoffee() {
//        return coffeeRepository.beanCoffee();
        List<Coffee> coffees= coffeeRepository.beanCoffee();
        return coffees.stream()
//                .filter(coffee -> coffee.getId()>2)
                .map(coffee -> new  CoffeeResponse(coffee.getName(),coffee.getDescription() ))
                .toList();
    }
}
