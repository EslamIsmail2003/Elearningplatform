package com.elearning.ELearningPlatform.Course;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {
    private String courseId; //readable
    private String courseName; //readable
    private String category;//readable
    private String status;//readable
    private String instructorName;//readable
    private String instructorId;
    private Integer duration;//readable
    private double price;
}
