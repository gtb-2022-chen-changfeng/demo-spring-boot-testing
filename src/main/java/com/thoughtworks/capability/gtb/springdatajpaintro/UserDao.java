package com.thoughtworks.capability.gtb.springdatajpaintro;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class UserDao {
    final AtomicLong userIdSeq = new AtomicLong();
    final Map<Long, User> users = new HashMap<>();

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public Optional<User> findOneById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public void save(User user) {
        user.setId(userIdSeq.incrementAndGet());
        users.put(user.getId(), user);
    }
}
