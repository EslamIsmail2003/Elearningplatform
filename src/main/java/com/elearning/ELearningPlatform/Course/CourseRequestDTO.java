package com.elearning.ELearningPlatform.Course;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseRequestDTO {
   private String courseName;
   private String category;
   private String instructorId;
   private String status;
   private Integer duration;
   private double price;
}
