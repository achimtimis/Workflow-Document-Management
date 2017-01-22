package com.proiectcolectiv.models.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by achy_ on 1/1/2017.
 */

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "document", schema = "public")
public class DocumentMetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String version;
    private String author;
    private String creationDate;
    private String abstractText;
    private String keywords;
    private String lastEditedOn;
    private String lastEditedBy;

    public DocumentMetaData(Long id, String version, String author, String creationDate, String abstractText, String keywords, String lastEditedOn, String lastEditedBy) {
        this.id = id;
        this.version = version;
        this.author = author;
        this.creationDate = creationDate;
        this.abstractText = abstractText;
        this.keywords = keywords;
        this.lastEditedOn = lastEditedOn;
        this.lastEditedBy = lastEditedBy;
    }

    public DocumentMetaData(String version, String author, String creationDate, String abstractText, String keywords, String lastEditedOn, String lastEditedBy) {
        this.version = version;
        this.author = author;
        this.creationDate = creationDate;
        this.abstractText = abstractText;
        this.keywords = keywords;
        this.lastEditedOn = lastEditedOn;
        this.lastEditedBy = lastEditedBy;
    }

}