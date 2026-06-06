package istad.ite.vanndeth.elearning.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
@Table (name = "student_profiles")
public class StudentProfile {
    @Id
    @Column(nullable = false, unique = true)
    private String userId;
    private String biography;
    private String facebook_link;
    private String github_link;
    private String major;
    private String phoneNumber;
    private String university;


}
