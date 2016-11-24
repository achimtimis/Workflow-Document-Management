package com.proiectcolectiv.service;

import com.proiectcolectiv.controller.IUserManagerEndpoint;
import com.proiectcolectiv.models.User;
import com.proiectcolectiv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by achy_ on 11/24/2016.
 */
public class UserManagerEndpoint implements IUserManagerEndpoint {


    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User create(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User delete(@PathVariable Long id) {
        User existingUser = userRepository.findOne(id);
        userRepository.delete(existingUser);
        return existingUser;
    }

    @Override
    public User update(@PathVariable Long id, User user) {
        User existingUser = userRepository.findOne(id);
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public User getUser(@PathVariable("id") Long id) {
        return userRepository.findOne(id);
    }
}
