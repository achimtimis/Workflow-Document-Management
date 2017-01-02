package com.proiectcolectiv.models.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Achim Timis on 7/6/2016.
 */

@Data
@Entity
@Table(name="users", schema = "public")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private UserRole role;


}
