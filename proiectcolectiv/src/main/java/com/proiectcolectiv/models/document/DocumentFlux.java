package com.proiectcolectiv.models.document;

import com.proiectcolectiv.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by achy_ on 1/1/2017.
 */
@Entity
@Table(name = "documentflux")
@Getter
@Setter
@NoArgsConstructor
public class DocumentFlux {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    private Document document;

    @OneToMany(targetEntity=User.class )
    private List<User> users;

    public DocumentFlux(Document document, List<User> users) {
        this.document = document;
        this.users = users;
    }

    public DocumentFlux(Long id,Document document, List users) {
        this.id = id;
        this.document = document;
        this.users = users;
    }
}
