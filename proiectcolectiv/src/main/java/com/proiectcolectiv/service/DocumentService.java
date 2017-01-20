package com.proiectcolectiv.service;

import com.proiectcolectiv.models.document.Document;
import com.proiectcolectiv.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by achy_ on 1/20/2017.
 */
@Component
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public Document createDocument(Document document) {
        return documentRepository.save(document);
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
}
