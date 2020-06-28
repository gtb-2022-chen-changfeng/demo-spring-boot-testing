package com.thoughtworks.capability.gtb.springdatajpaintro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@RestController
public class EducationController {

    private final UserService userService;

    public EducationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userId}/educations")
    public List<Education> getEducationsByUserId(@PathVariable Long userId) {
        return userService.getEducationsForUser(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/users/{userId}/educations")
    public void addEducationForUser(@PathVariable Long userId, @RequestBody Education education) {
        userService.addEducationForUser(userId, education);
    }
}
