package com.thoughtworks.capability.gtb.springdatajpaintro;

import lombok.Data;

@Data
public class Education {
    private Long userId;
    private final Long year;
    private final String title;
    private final String description;

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
