package com.fizikovnet.hw4.services;

import com.fizikovnet.hw4.dao.UserDAO;
import com.fizikovnet.hw4.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<User> findById(long id) {
        return Optional.of(userDAO.findById(id));
    }

    public Optional<List<User>> findAll() {
        return Optional.of(userDAO.findAll());
    }

    public void save(User user) {
        userDAO.create(user);
    }

    public void update(User user) {
        if (user.getId() == null) {
            throw new RuntimeException("User should have id!");
        }
        userDAO.update(user);
    }

    public void delete(User user) {
        userDAO.delete(user);
    }

}
