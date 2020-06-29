package com.thoughtworks.capability.gtb.springdatajpaintro;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    final UserRepository userRepository;
    final EducationDao educationDao;

    public UserService(UserRepository userRepository, EducationDao educationDao) {
        this.userRepository = userRepository;
        this.educationDao = educationDao;
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
        findById(userId);
        return educationDao.findAllByUserId(userId);
    }

    public void addEducationForUser(Long userId, Education education) {
        findById(userId);
        educationDao.save(education);
    }
}
