package com.thoughtworks.capability.gtb.springdatajpaintro;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EducationController {

    private final UserService userService;

    @GetMapping("/users/{userId}/educations")
    public List<Education> getEducationsByUserId(@PathVariable Long userId) {
        return userService.getEducationsForUser(userId);
    }

    @PostMapping("/users/{userId}/educations")
    public void addEducationForUser(@PathVariable Long userId, @RequestBody Education education) {
        userService.addEducationForUser(userId, education);
    }
}
