package com.thoughtworks.capability.gtb.springdatajpaintro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private Long id;
    private String name;
    private Long age;
    private String avatar;
    private String description;
    @JsonIgnore
    private List<Education> educations = new ArrayList<>();

    public void addEducation(Education education) {
        educations.add(education);
    }
}
