package com.proiectcolectiv.repository;

import com.proiectcolectiv.models.user.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by achy_ on 1/21/2017.
 */
public interface UserGroupRepository extends JpaRepository<UserGroup, Long>{
    List<UserGroup> findByName(String name);
}
