package com.elearning.ELearningPlatform.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponseException {
    private Integer status;
    private String message;
    private LocalDateTime timestamp;
}
