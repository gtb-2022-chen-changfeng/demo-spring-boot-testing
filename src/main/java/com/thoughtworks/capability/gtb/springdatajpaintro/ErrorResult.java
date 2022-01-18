package com.thoughtworks.capability.gtb.springdatajpaintro;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResult {
    private int status;
    private String error;
    private String message;

    public ErrorResult(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

}
