package ite.spring_boot.assignment_1.controller;

import ite.spring_boot.assignment_1.entity.Coffee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CoffeeController {

    private final List<Coffee> coffeeList;
    public CoffeeController(List<Coffee> coffeeList){
        this.coffeeList = coffeeList;
    }
    @RequestMapping("/coffees")
    public List<Coffee> getCoffeeList(){
        return coffeeList;
    }
}
