package com.proiectcolectiv.service;

import com.proiectcolectiv.models.document.UserDocumentMapping;
import com.proiectcolectiv.models.user.User;
import com.proiectcolectiv.models.user.UserGroup;
import com.proiectcolectiv.repository.UserDocumentMappingRepository;
import com.proiectcolectiv.repository.UserGroupRepository;
import com.proiectcolectiv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

/**
 * Created by achy_ on 1/20/2017.
 */
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private UserDocumentMappingRepository userDocumentMappingRepository;

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User logIn(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User createUser(@RequestBody User user) throws Exception{
        if (!isUserRegistered(user.getUsername())){
            return userRepository.saveAndFlush(user);
        }else{
            throw new Exception("User already registered");
        }

    }

    public User deleteUser(@PathVariable Long id){
        User existingUser = userRepository.findOne(id);
        List<UserDocumentMapping> mappings = userDocumentMappingRepository.findAll();
        for (UserDocumentMapping u : mappings){
            if (u.getUser().getId() == existingUser.getId()){
                userDocumentMappingRepository.delete(u);
            }
        }
        for (UserGroup u : userGroupRepository.findAll()){
//            for (User us : u.getUsers()){
            List<User> users = u.getUsers();
            for (int i = 0; i<users.size();i++){
                if (users.get(i).getId() == id){
//                    userGroupRepository.delete(u);
                    users.remove(i);
                    u.setUsers(users);
                    userGroupRepository.save(u);
                }

            }
        }
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


    public void addUserToGroup(String info, User user) {
        List<UserGroup> users = findByGroup(info);
        if (users.size() != 0) {
            for (UserGroup userGroup : users) {
                userGroup.getUsers().add(user);
                userGroupRepository.save(userGroup);
            }
        }else{
            userGroupRepository.save(new UserGroup(info, Arrays.asList(user)));
        }
    }

    public List<UserGroup> getAllGroups() {
        return userGroupRepository.findAll();
    }

    private boolean isUserRegistered(String username){
        if (userRepository.findByUsername(username) != null){
            return true;
        }
        return false;
    }
    private List<UserGroup> findByGroup(String name){
        return userGroupRepository.findByName(name);
    }
}
