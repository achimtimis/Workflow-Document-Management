package com.proiectcolectiv.models.document;

import com.proiectcolectiv.models.user.UserGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @OneToOne
    private Document document;

    @OneToOne
    private UserGroup group;

    private int hashCode;

    public DocumentFlux(Document document, UserGroup group,int hashCode) {
        this.document = document;
        this.group = group;
        this.hashCode = hashCode;
    }

    public DocumentFlux(Long id,Document documents, UserGroup group,int hashCode) {
        this.id = id;
        this.document= documents;
        this.group = group;
        this.hashCode = hashCode;
    }
}
