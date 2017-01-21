package com.proiectcolectiv.controller;

import com.proiectcolectiv.models.user.User;
import com.proiectcolectiv.models.user.UserGroup;
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
    public User createUser(@RequestBody User user) throws Exception{
        User result = null;
        try {
            return userService.createUser(user);
        }
        catch (Exception e){
            //
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable Long id){
        User existingUser = userService.deleteUser(id);
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

    @RequestMapping(value = "/addToGroup/{groupname}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addUserToUserGroup(@PathVariable("groupname") String groupName, @RequestBody User user){
        userService.addUserToGroup(groupName,user);

    }
    @RequestMapping(value = "/groups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserGroup> getAllUserGroups(){
        return userService.getAllGroups();
    }

}
