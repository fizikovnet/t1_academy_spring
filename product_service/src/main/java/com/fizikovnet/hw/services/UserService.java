package com.fizikovnet.hw.services;

import com.fizikovnet.hw.entity.User;
import com.fizikovnet.hw.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public Optional<List<User>> findAll() {
        return Optional.of(userRepository.findAll());
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        if (user.getId() == null) {
            throw new RuntimeException("User should have id!");
        }
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

}
