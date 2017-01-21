package com.proiectcolectiv.models.workzones;

import com.proiectcolectiv.models.document.Document;
import com.proiectcolectiv.models.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by achy_ on 1/1/2017.
 */
@Data
@Entity
@Table(name = "activewz")
public class ActiveWorkZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(targetEntity=Document.class )
    private List<Document> activeDocuments;

    public ActiveWorkZone(List<Document> activeDocuments) {
        this.activeDocuments = activeDocuments;
    }
    public ActiveWorkZone(Long id,List<Document> activeDocuments) {
        this.id = id;
        this.activeDocuments = activeDocuments;
    }
}
