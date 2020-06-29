package com.thoughtworks.capability.gtb.springdatajpaintro;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String name;
    private Long age;
    private String avatar;
    private String description;
}
