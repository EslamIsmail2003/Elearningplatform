package com.elearning.ELearningPlatform.Course;

import com.elearning.ELearningPlatform.utils.BaseEntity;
import com.elearning.ELearningPlatform.Instructor.Instructor;
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
public class Course extends BaseEntity {
    @Column(name = "name")
    private String courseName;
    private String category;
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
    @Enumerated(EnumType.STRING)
    private CourseStatus status;
    private int duration;
    private double price;

}
