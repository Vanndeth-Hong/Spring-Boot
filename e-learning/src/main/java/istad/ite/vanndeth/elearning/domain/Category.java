package istad.ite.vanndeth.elearning.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String icon;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false, length = 50)
    private String name;
}
