package com.proiectcolectiv.controller;

import com.proiectcolectiv.models.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by achy_ on 11/24/2016.
 */

@RestController
@RequestMapping("/users")
public interface IUserManagerEndpoint {


    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll();

    @RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.GET,produces = MediaType.TEXT_PLAIN_VALUE)
    String logIn(@PathVariable(value = "username") String username, @PathVariable(value = "password") String password);

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody User user);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User delete(@PathVariable Long id);


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public User update(@PathVariable Long id, @RequestBody User user);


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("id") Long id);


}
