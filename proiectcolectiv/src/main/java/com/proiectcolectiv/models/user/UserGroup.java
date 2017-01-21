package com.proiectcolectiv.models.user;

import com.proiectcolectiv.models.document.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by achy_ on 1/21/2017.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "usergroups")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(targetEntity=User.class )
    List<User> users;

    public UserGroup(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }
    public UserGroup(Long id,String name, List<User> users) {
        this.id  = id;
        this.name = name;
        this.users = users;
    }
}
