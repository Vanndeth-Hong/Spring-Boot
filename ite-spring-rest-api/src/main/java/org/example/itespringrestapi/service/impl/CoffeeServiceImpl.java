package org.example.itespringrestapi.service.impl;

import org.example.itespringrestapi.domain.Coffee;
import org.example.itespringrestapi.dto.CoffeeResponse;
import org.example.itespringrestapi.dto.CreateCoffeeRequest;
import org.example.itespringrestapi.dto.UpdateCoffeeRequest;
import org.example.itespringrestapi.repository.CoffeeRepository;
import org.example.itespringrestapi.service.CoffeeService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException; // Changed import

import java.util.List;
import java.util.Random;

@Service
public class CoffeeServiceImpl implements CoffeeService {

    private final CoffeeRepository coffeeRepository;


    public CoffeeServiceImpl (CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public void deleteCoffeeById(Integer id){
        boolean removed = coffeeRepository.getCoffees()
            .removeIf(c->c.getId().equals(id));
        if(!removed){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Could not delete coffee: No record found with ID %d.",id));
        }
}

    @Override
    public CoffeeResponse updateCoffeeById(Integer id, UpdateCoffeeRequest updateCoffeeRequest){
       return coffeeRepository.getCoffees()
                .stream()
                .filter(coffee -> coffee.getId().equals(id))
                .findFirst()
                .map(oldCoffee -> {
                    oldCoffee.setName(updateCoffeeRequest.name());
                    oldCoffee.setDescription(updateCoffeeRequest.description());
                    oldCoffee.setPrice(updateCoffeeRequest.price());
                    return oldCoffee;
                })
                .map(newCoffee -> new CoffeeResponse(newCoffee.getId(), newCoffee.getName(), newCoffee.getDescription(), newCoffee.getPrice()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Coffee ID = %d doesn't exist in database", id)));
    }

    @Override
    public CoffeeResponse getCoffeeById(Integer id) {
        return coffeeRepository.getCoffees()
                .stream()
                .filter(c -> c.getId().equals(id))
                .map(c -> new CoffeeResponse(c.getId(), c.getName(), c.getDescription(), c.getPrice()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        String.format("Coffee ID = %d not found", id)));
    }

    @Override
    public List<CoffeeResponse> searchByNameAndPrice(String name, Double price){
        return coffeeRepository.getCoffees()
                .stream()
                .filter(s -> s.getName().contains(name) && s.getPrice().equals(price))
                .map(s -> new CoffeeResponse(s.getId(), s.getName(), s.getDescription(), s.getPrice()))
                .toList();
    }

    @Override
    public CoffeeResponse createCoffee(CreateCoffeeRequest createCoffeeRequest){
        Coffee coffee = new Coffee();
        coffee.setId(new Random().nextInt(100)); 
        coffee.setName(createCoffeeRequest.name());
        coffee.setDescription(createCoffeeRequest.description());
        coffee.setPrice(createCoffeeRequest.price());

        boolean isExisting = coffeeRepository.getCoffees()
                .stream()
                .anyMatch(c -> c.getId().equals(coffee.getId()));
        if(isExisting){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Coffee ID already exists.");
        }


        return new CoffeeResponse(
                coffee.getId(), coffee.getName(),
                coffee.getDescription(),
                coffee.getPrice()
        );
    }

    @Override
    public List<Coffee> getCoffee() {
        return coffeeRepository.getCoffees();
    }
}
