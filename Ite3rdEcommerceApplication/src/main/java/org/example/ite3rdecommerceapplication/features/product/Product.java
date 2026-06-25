package org.example.ite3rdecommerceapplication.features.product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.ite3rdecommerceapplication.features.category.Category;
import org.example.ite3rdecommerceapplication.features.order.OrderLine;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table( name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Column( length = 100, nullable = false,unique = true)
    private String code;
    @Column(  nullable = false,unique = true)
    private String slug; // For SEO
    @Column(  nullable = false)
    private String name;
    @Column( length = 500)
    private String description;
    @Column(  nullable = false)
    private String thumbnail;
    @Column(  nullable = false)
    private BigDecimal price;
    @Column(  nullable = false)
    private Integer qty;
    @Column(  nullable = false)
    private  Boolean isAvailable;
    @Column(  nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLine;
}