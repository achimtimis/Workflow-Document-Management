package com.proiectcolectiv.models.document;

import com.proiectcolectiv.models.user.User;
import com.proiectcolectiv.models.user.UserGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.print.Doc;
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

    @OneToMany(targetEntity=Document.class )
    private List<Document> documents;

    @OneToMany(targetEntity=UserGroup.class )
    private List<UserGroup> users;

    public DocumentFlux(List<Document> documents, List<UserGroup> users) {
        this.documents = documents;
        this.users = users;
    }

    public DocumentFlux(Long id,List documents, List<UserGroup> users) {
        this.id = id;
        this.documents = documents;
        this.users = users;
    }
}
