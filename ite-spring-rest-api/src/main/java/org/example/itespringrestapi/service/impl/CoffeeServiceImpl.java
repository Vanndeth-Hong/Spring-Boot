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
    public List<Coffee> getCoffee() {
        return coffeeRepository.beanCoffee();
    }




    @Override
    public CoffeeResponse getCoffeeById(Integer id) {
        CoffeeResponse coffee = coffeeRepository.beanCoffee()
                .stream()
                .filter(c -> c.getId().equals(id))
                .map(c -> new CoffeeResponse(c.getName(), c.getDescription(), c.getPrice()))
                .findFirst().orElseThrow(() -> new RuntimeException("Not Found"));
        return coffee;

    }

    @Override
    public List<CoffeeResponse> searchByNameandPrice(String name, Double price){
        List<CoffeeResponse> search = coffeeRepository.beanCoffee()
                .stream()
                .filter(s -> s.getName().contains(name) && s.getPrice().equals(price))
                .map(s-> new CoffeeResponse(s.getName(), s.getDescription(), s.getPrice()))
                .toList();
        return search;
    }
}
