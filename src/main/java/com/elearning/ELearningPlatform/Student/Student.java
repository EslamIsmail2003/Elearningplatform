package com.elearning.ELearningPlatform.Student;

import com.elearning.ELearningPlatform.utils.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name= "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Student extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String country;
}
