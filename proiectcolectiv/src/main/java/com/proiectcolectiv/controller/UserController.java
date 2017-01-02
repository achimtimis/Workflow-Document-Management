package com.proiectcolectiv.controller;

import com.proiectcolectiv.models.user.User;
import com.proiectcolectiv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by achy_ on 11/24/2016.
 */
@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    User logIn(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestBody User user){
        return userRepository.saveAndFlush(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable Long id){
        User existingUser = userRepository.findOne(id);
        userRepository.delete(existingUser);
        return existingUser;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public User update(@PathVariable Long id, @RequestBody User user){
        User existingUser = userRepository.findOne(id);
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        userRepository.save(existingUser);
        return existingUser;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("id") Long id){
        return userRepository.findOne(id);
    }
}
