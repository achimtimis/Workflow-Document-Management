package com.proiectcolectiv.models.document;

import lombok.*;

import javax.persistence.*;

/**
 * Created by achy_ on 1/1/2017.
 */


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Document extends DocumentMetaData {

    public Document(Long id, String version, String author, String creationDate, String abstractText,
                    String keywords, String lastEditedOn, String lastEditedBy) {
        super(id, version, author, creationDate, abstractText, keywords, lastEditedOn, lastEditedBy);
    }

    public Document(Long id, String version, String author, String creationDate, String abstractText,
                    String keywords, String lastEditedOn, String lastEditedBy, String name,
                    String details, String documentType, DocumentStatus status) {
        super(id, version, author, creationDate, abstractText, keywords, lastEditedOn, lastEditedBy);
        this.name = name;
        this.details = details;
        this.documentType = documentType;
        this.status = status;
    }
    public Document(String version, String author, String creationDate, String abstractText,
                    String keywords, String lastEditedOn, String lastEditedBy, String name,
                    String details, String documentType, DocumentStatus status) {
        super(version, author, creationDate, abstractText, keywords, lastEditedOn, lastEditedBy);
        this.name = name;
        this.details = details;
        this.documentType = documentType;
        this.status = status;
    }
    private String name;

    private String details;

    private String documentType;

    private DocumentStatus status;


}
