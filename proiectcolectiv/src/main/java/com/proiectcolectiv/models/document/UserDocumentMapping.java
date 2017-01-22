package com.proiectcolectiv.models.document;

import com.proiectcolectiv.models.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by achy_ on 1/21/2017.
 */
@Entity
@Table(name = "userdocument")
@Getter
@Setter
@NoArgsConstructor
public class UserDocumentMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;

    @OneToOne
    private Document document;

    public UserDocumentMapping(User user, Document document) {
        this.user = user;
        this.document = document;
    }

    public UserDocumentMapping(Long id, User user, Document document) {
        this.id = id;
        this.user = user;
        this.document = document;
    }
}
