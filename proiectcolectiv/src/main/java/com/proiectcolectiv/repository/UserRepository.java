package com.proiectcolectiv.repository;

import com.proiectcolectiv.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by achy_ on 11/24/2016.
 */
public interface UserRepository extends JpaRepository<User,Long> {


    @Query("select u from User u where u.username = :username and u.password = :password")
    User getByUsernameAndPassword(String username, String password);
}
