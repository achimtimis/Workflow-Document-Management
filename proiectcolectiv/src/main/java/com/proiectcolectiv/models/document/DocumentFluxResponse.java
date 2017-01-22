package com.proiectcolectiv.models.document;

import com.proiectcolectiv.models.user.UserGroup;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by achy_ on 1/21/2017.
 */
//@Getter
//@Setter
@NoArgsConstructor
public class DocumentFluxResponse {

    private Long id;
    private List<Document> documents;
    private List<UserGroup> groups;

    public DocumentFluxResponse(List<Document> documents, List<UserGroup> groups) {
        this.documents = documents;
        this.groups = groups;
    }

    public DocumentFluxResponse(Long id, List documents, List<UserGroup> groups) {
        this.id = id;
        this.documents = documents;
        this.groups = groups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<UserGroup> groups) {
        this.groups = groups;
    }
}

