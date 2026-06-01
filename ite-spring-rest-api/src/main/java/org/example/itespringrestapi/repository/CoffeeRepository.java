package org.example.itespringrestapi.repository;

import org.example.itespringrestapi.domain.Coffee;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CoffeeRepository {

    private List<Coffee> coffees;

    public CoffeeRepository(){
        this.coffees = new ArrayList<>();

        Coffee c1 = new Coffee(1, "Ice Latte", "50%", BigDecimal.valueOf(2.5));
        Coffee c2 = new Coffee(2, "Hot Latte", "75%",BigDecimal.valueOf(3.5));
        Coffee c3 = new Coffee(3, "Cappuccino", "100%",BigDecimal.valueOf(4.5));
        Coffee c4 = new Coffee(4, "Americano", "25%", BigDecimal.valueOf(5.0));

        coffees.add(c1);
        coffees.add(c2);
        coffees.add(c3);
        coffees.add(c4);
    }


    public List<Coffee> getCoffees(){
        return coffees;
    }
}
