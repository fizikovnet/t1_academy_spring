package com.fizikovnet.hw4;

import com.fizikovnet.hw4.entity.User;
import com.fizikovnet.hw4.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;
import java.util.List;

@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        UserService userService = context.getBean(UserService.class);

        // Create User
        User user = new User("Vasya");
        userService.save(user);

        // Get All Users
        List<User> users = userService.findAll().orElse(Collections.emptyList());
        users.forEach(System.out::println);

        // Get User by ID
        User existUser = userService.findById(users.get(0).getId()).orElseThrow(RuntimeException::new);
        System.out.println(existUser);

        // Update User
        existUser.setUsername("Petr");
        userService.update(existUser);
        User updatedUser = userService.findById(existUser.getId()).orElseThrow(RuntimeException::new);
        assert existUser.equals(updatedUser.getUsername());

        // Delete User
        userService.delete(existUser);
    }

}
