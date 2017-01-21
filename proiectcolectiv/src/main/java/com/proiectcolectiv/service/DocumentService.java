package com.proiectcolectiv.service;

import com.proiectcolectiv.models.document.Document;
import com.proiectcolectiv.models.document.UserDocument;
import com.proiectcolectiv.models.document.UserDocumentMapping;
import com.proiectcolectiv.models.user.User;
import com.proiectcolectiv.repository.DocumentRepository;
import com.proiectcolectiv.repository.UserDocumentMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by achy_ on 1/20/2017.
 */
@Component
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserDocumentMappingRepository userDocumentMappingRepository;

    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    public Document createUserDocument(UserDocument document){
        Document document1 = document.getDocument();
        document1.setAuthor(document.getUser().getUsername());
        document1.setCreationDate(new Date().toString());
        document1.setLastEditedBy(document.getUser().getUsername());
        document1.setLastEditedOn(new Date().toString());
        Document result = documentRepository.save(document.getDocument());
        userDocumentMappingRepository.save(new UserDocumentMapping(document.getUser(),result));
        return result;
    }

    public Document getDocumentById(int id) {
        return documentRepository.getOne(Long.valueOf(id));
    }

    public Document updateDocument(int id, Document document) {
        Document found = documentRepository.findOne(Long.valueOf(id));
        if (found != null) {
            document.setId(Long.valueOf(id));
            return documentRepository.saveAndFlush(document);
        }
        return null;
    }

    public void deleteDocumentById(int id) {
        Document found = documentRepository.getOne(Long.valueOf(id));
        documentRepository.delete(found);
    }


    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public List<Document> getAllDocumentsByUser(int id) {
        List<Document> result = new ArrayList<>();
        List<UserDocumentMapping> list = userDocumentMappingRepository.findAll();
        for (UserDocumentMapping m : list){
            if (m.getUser().getId() == id) {
                result.add(documentRepository.getOne(m.getDocument().getId()));
            }
        }
        return result;
    }
}
