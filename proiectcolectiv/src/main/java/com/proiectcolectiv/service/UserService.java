package com.proiectcolectiv.service;

import com.proiectcolectiv.models.user.User;
import com.proiectcolectiv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by achy_ on 1/20/2017.
 */
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User logIn(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User createUser(@RequestBody User user){
        return userRepository.saveAndFlush(user);
    }

    public User deleteUser(@PathVariable Long id){
        User existingUser = userRepository.findOne(id);
        userRepository.delete(existingUser);
        return existingUser;
    }

    public User updateUser(@PathVariable Long id, @RequestBody User user){
        User existingUser = userRepository.findOne(id);
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());
        userRepository.save(existingUser);
        return existingUser;
    }

    public User getUserById(@PathVariable("id") Long id){
        return userRepository.findOne(id);
    }
}
