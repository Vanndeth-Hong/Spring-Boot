package istad.ite.vanndeth.elearning.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime enrolledAt;
    private LocalDateTime paymentAt;
    private String paymentMethod;
    private Boolean paymentStatus;

    @ManyToOne
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentProfile student;


}
