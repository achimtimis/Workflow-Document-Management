package com.proiectcolectiv.models.document;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by achy_ on 1/1/2017.
 */

@Data
@Entity
@Table(name="document", schema = "public")
public class Document extends DocumentMetaData{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String details;

    private String documentType;

    private DocumentStatus status;





}
