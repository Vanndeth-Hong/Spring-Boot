package istad.ite.vanndeth.elearning.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer countRating;
    private LocalDate createdAt;
    private String description;
    private Float discountPercent;
    private Boolean isDeleted;
    private Boolean isPublished;
    private String keyword;
    private String level;
    @Column(precision = 38, scale = 2)
    private BigDecimal price;
    private String slug;
    private Float starRating;
    private String thumbnail;
    private String title;
    private Float totalHours;
    private LocalDate updatedAt;

    @ManyToOne
    private Category category;

    @ManyToOne
    private InstructorProfile instructorProfiles;



}
