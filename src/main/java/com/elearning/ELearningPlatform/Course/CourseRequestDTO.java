package com.elearning.ELearningPlatform.Course;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseRequestDTO {
   @NotBlank(message = "Course name is required!")
   private String courseName;
   @NotBlank(message = "Category is required!")
   private String category;
   @NotBlank(message = "Instructor id is required!")
   private String instructorId;
   @NotBlank(message = "Status is required!")
   private String status;
   @NotNull(message = "Duration is required!")
   @Positive(message = "Duration can't be negative!")
   private Integer duration;
   @NotNull(message = "Price is required!")
   @Positive(message = "Price can't be negative!")
   private Double price;
}
