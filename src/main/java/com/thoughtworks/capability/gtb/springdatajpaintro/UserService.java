package com.thoughtworks.capability.gtb.springdatajpaintro;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    final AtomicLong userIdSeq = new AtomicLong();
    final Map<Long, User> users = new HashMap<>();
    final Map<Long, List<Education>> educations = new HashMap<>();

    public List<User> findUsers() {
        return new ArrayList<>(users.values());
    }

    public User findById(Long id) {
        return Optional.ofNullable(users.get(id))
                .orElseThrow(() -> new UserNotExistedException("User Not Found"));
    }

    public Long createUser(User user) {
        user.setId(userIdSeq.incrementAndGet());
        users.put(user.getId(), user);
        // initialize educations for new user
        educations.put(user.getId(), new ArrayList<>());
        return user.getId();
    }

    public List<Education> getEducationsForUser(Long userId) {
        return Optional.ofNullable(educations.get(userId))
                .orElseThrow(() -> new UserNotExistedException("User Not Found"));
    }

    public void addEducationForUser(Long userId, Education education) {
        getEducationsForUser(userId).add(education);
    }
}
