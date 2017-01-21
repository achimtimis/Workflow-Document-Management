package com.proiectcolectiv.repository;

import com.proiectcolectiv.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by achy_ on 11/24/2016.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
}
