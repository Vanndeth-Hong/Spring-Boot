package ite.spring_boot.assignment_1.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Coffee {
    public String code;
    public String name;
    public double price;
    private boolean isAvailable;

}
