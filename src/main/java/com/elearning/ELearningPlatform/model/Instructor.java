package com.elearning.ELearningPlatform.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends BaseEntity {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String country;
    private String scope;
}
