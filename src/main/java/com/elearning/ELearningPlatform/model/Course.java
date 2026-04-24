package com.elearning.ELearningPlatform.model;

import com.elearning.ELearningPlatform.enums.CourseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity{
    private String name;
    private String category;
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
    @Enumerated(EnumType.STRING)
    private CourseStatus status;
    private int duration;
    private double price;
}
