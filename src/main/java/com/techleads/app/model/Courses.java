package com.techleads.app.model;

import com.techleads.app.validators.IConstraintListCourse;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Validated
@ToString
public class Courses {

    private Integer id;
    @NotBlank(message = "Course Name must not be empty")
    @IConstraintListCourse
    private String courseName;
}
