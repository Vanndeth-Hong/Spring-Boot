package org.example.itespringrestapi.service.impl;

import org.example.itespringrestapi.domain.Coffee;
import org.example.itespringrestapi.dto.CoffeeResponse;
import org.example.itespringrestapi.dto.CreateCoffeeRequest;
import org.example.itespringrestapi.repository.CoffeeRepository;
import org.example.itespringrestapi.service.CoffeeService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CoffeeServiceImpl implements CoffeeService {

    private final CoffeeRepository coffeeRepository;
//    private final List<Coffee> coffeeBean;

    public CoffeeServiceImpl (CoffeeRepository coffeeRepository){
        this.coffeeRepository= coffeeRepository;
    }



    @Override
    public CoffeeResponse getCoffeeById(Integer id) {
        CoffeeResponse coffee = coffeeRepository.getCoffees()
                .stream()
                .filter(c -> c.getId().equals(id))
                .map(c -> new CoffeeResponse(c.getName(), c.getDescription(), c.getPrice()))
                .findFirst().orElseThrow(() -> new RuntimeException("Not Found"));
        return coffee;

    }

    @Override
    public List<CoffeeResponse> searchByNameAndPrice(String name, Double price){
        List<CoffeeResponse> search = coffeeRepository.getCoffees()
                .stream()
                .filter(s -> s.getName().contains(name) && s.getPrice().equals(price))
                .map(s-> new CoffeeResponse(s.getName(), s.getDescription(), s.getPrice()))
                .toList();
        return search;
    }

    @Override
    public CoffeeResponse createCoffee(CreateCoffeeRequest createCoffeeRequest){
        Coffee coffee = new Coffee();
        coffee.setId(new Random().nextInt(100)); //system Generated data
        coffee.setName(createCoffeeRequest.name());
        coffee.setDescription(createCoffeeRequest.description());
        coffee.setPrice(createCoffeeRequest.price());

        boolean isExisting = coffeeRepository.getCoffees()
                .stream()
                .anyMatch(c->c.getId().equals(coffee.getId()));
        if(isExisting){
            throw new RuntimeException("Coffee ID is exiting.");
        }
        return new CoffeeResponse(
                coffee.getName(),
                coffee.getDescription(),
                coffee.getPrice()
        );
    }

    @Override
    public List<Coffee> getCoffee() {
        return coffeeRepository.getCoffees();
    }

}
