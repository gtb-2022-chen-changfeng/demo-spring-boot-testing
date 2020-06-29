package com.thoughtworks.capability.gtb.springdatajpaintro;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findOneById(id)
                .orElseThrow(() -> new UserNotExistedException("User Not Found"));
    }

    public Long createUser(User user) {
        userRepository.save(user);
        return user.getId();
    }

    public List<Education> getEducationsForUser(Long userId) {
        User user = findById(userId);
        return user.getEducations();
    }

    public void addEducationForUser(Long userId, Education education) {
        User user = findById(userId);
        user.addEducation(education);
    }
}
