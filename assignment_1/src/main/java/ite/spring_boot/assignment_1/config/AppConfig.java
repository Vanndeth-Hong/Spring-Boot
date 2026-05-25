package ite.spring_boot.assignment_1.config;
import ite.spring_boot.assignment_1.entity.Coffee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public Coffee espresso(){
        Coffee c = new Coffee();
        c.setCode("C001");
        c.setName("Espresso");
        c.setPrice(2.50);
        c.setAvailable(true);
        return c;
    }

    @Bean
    public Coffee latte(){
        Coffee c = new Coffee();
        c.setCode("C002");
        c.setName("Latte");
        c.setPrice(3.50);
        c.setAvailable(true);
        return c;
    }
    @Bean
    public Coffee cappuccino(){
        Coffee c = new Coffee();
        c.setCode("C003");
        c.setName("Cappuccino");
        c.setPrice(3.00);
        c.setAvailable(false);
        return c;
    }
    @Bean
    public List<Coffee> coffeeList(Coffee espresso, Coffee latte, Coffee cappuccino){
        List<Coffee> coffees = new ArrayList<>();
        coffees.add(espresso);
        coffees.add(latte);
        coffees.add(cappuccino);
        return coffees;
    }
}
