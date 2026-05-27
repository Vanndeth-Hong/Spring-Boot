package org.example.itespringrestapi.repository;

import org.example.itespringrestapi.domain.Coffee;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class CoffeeRepository {

    @Bean
    public List<Coffee> beanCoffee(){
        Coffee c1 = new Coffee(1, "Ice Latte", "50%");
        Coffee c2 = new Coffee(2, "Hot Latte", "75%");
        Coffee c3 = new Coffee(3, "Cappocino", "100%");
        return Arrays.asList(c1, c2, c3);
    }
}
