package com.proiectcolectiv.models.document;

import com.proiectcolectiv.models.user.UserGroup;
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

    @OneToMany(targetEntity=Document.class)
    private List<Document> documents;

    @OneToMany(targetEntity=UserGroup.class )
    private List<UserGroup> groups;

    public DocumentFlux(List<Document> documents, List<UserGroup> groups) {
        this.documents = documents;
        this.groups = groups;
    }

    public DocumentFlux(Long id,List documents, List<UserGroup> groups) {
        this.id = id;
        this.documents = documents;
        this.groups = groups;
    }
}
