package com.thoughtworks.capability.gtb.springdatajpaintro;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    final AtomicLong userIdSeq = new AtomicLong();
    final Map<Long, User> users = new HashMap<>();

    List<User> findUsers() {
        return new ArrayList<>(users.values());
    }

    Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    Long createUser(User user) {
        user.setId(userIdSeq.incrementAndGet());
        users.put(user.getId(), user);
        return user.getId();
    }
}
