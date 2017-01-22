package com.proiectcolectiv.models.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Achim Timis on 7/6/2016.
 */

@Data
@Entity
@Table(name = "users", schema = "public")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private UserRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserRole getRole() {
        return this.role;
    }

    public User(Long id, String username, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    User() {
        this.id = null;
        this.username = null;
        this.password = null;
        this.role = null;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Username: " + username + " Password: " + password + " Role: " + role.toString();
    }
}
