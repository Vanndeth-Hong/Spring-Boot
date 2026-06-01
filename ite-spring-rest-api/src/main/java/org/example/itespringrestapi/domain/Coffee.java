package org.example.itespringrestapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Coffee {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
}
