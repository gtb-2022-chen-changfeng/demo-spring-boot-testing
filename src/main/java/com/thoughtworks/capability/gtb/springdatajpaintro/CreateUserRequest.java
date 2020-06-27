package com.thoughtworks.capability.gtb.springdatajpaintro;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private Long age;
    private String avatar;
    private String description;
}
