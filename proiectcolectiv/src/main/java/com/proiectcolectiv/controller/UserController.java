package com.proiectcolectiv.controller;

import com.proiectcolectiv.models.user.User;
import com.proiectcolectiv.repository.UserRepository;
import com.proiectcolectiv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by achy_ on 11/24/2016.
 */
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    User logIn(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){
        return userService.logIn(username, password);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable Long id){
        User existingUser = userService.getUserById(id);
        userService.deleteUser(existingUser.getId());
        return existingUser;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public User update(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id,user);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

}
