package org.example.itespringrestapi.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.itespringrestapi.domain.Coffee;
import org.example.itespringrestapi.dto.CoffeeResponse;
import org.example.itespringrestapi.service.CoffeeService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/coffees")
public class CoffeeController {
    private final CoffeeService coffeeService;
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping
    public List<Coffee> getCoffee() {

        return coffeeService.getCoffee();

    }

    @GetMapping("/{id}")
    public CoffeeResponse getCoffeeById(@PathVariable Integer id){
        log.info("GET id: {}", id);
        return coffeeService.getCoffeeById(id);
    }

    @GetMapping("/search")
    public List<CoffeeResponse> searchCoffeesByName(
            @RequestParam(required = false, defaultValue = "Null") String name,
            @RequestParam(required = false, defaultValue = "0.0") Double price
    ){
        log.info("GET search name: {}", name);
        log.info("GET search price: {}", price );

        return coffeeService.searchByNameAndPrice(name,price);
    }


}

