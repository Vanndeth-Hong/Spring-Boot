package istad.ite.vanndeth.elearning.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "instructor_profiles")
public class InstructorProfile {
    @Id
    @Column(nullable = false, unique = true)
    private String userId;
    private String biography;
    private String facebook_link;
    private String github_link;
    private String jobTitle;
    private String phoneNumber;

}
