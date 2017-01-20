package com.proiectcolectiv.controller;

import com.proiectcolectiv.models.document.Document;
import com.proiectcolectiv.models.document.DocumentStatus;
import com.proiectcolectiv.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by achy_ on 1/1/2017.
 */
@RestController
@CrossOrigin
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @RequestMapping(method = RequestMethod.POST)
    public Document createDocument(@RequestBody Document document) {
        return documentService.createDocument(document);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Document> getAllDocuments() {
//        Document d = new Document("a","a","a","a","a","a","a","a","a","a", DocumentStatus.BLOCKED);
//        documentService.createDocument(d);
        return documentService.getAllDocuments();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDocument(@PathVariable int id) {
        documentService.deleteDocumentById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Document updateDocument(@PathVariable int id, @RequestBody Document document) {
        return documentService.updateDocument(id, document);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Document getDocumentbyId(@PathVariable int id) {
        return documentService.getDocumentById(id);
    }


}
