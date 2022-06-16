package com.techleads.app.model;

import javax.validation.constraints.NotBlank;

public class Skill {
    private Integer id;
    @NotBlank(message = "skillName must not be empty")
    private String skillName;
}
