package com.proiectcolectiv.models.document;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by achy_ on 1/1/2017.
 */

@Data
public class DocumentMetaData {

    private String version;
    private String author;
    private Date creationDate;
    private String abstractText;
    private List<String> keywords;
    private Date lastEditedOn;
    private String lastEditedBy;


}
