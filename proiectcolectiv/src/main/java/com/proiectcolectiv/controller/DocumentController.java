package com.proiectcolectiv.controller;

import com.proiectcolectiv.models.document.Document;
import com.proiectcolectiv.models.document.DocumentFluxResponse;
import com.proiectcolectiv.models.document.DocumentStatus;
import com.proiectcolectiv.models.document.UserDocument;
import com.proiectcolectiv.models.user.User;
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
        return documentService.getAllDocuments();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDocument(@PathVariable int id) {
        documentService.deleteDocumentById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Document updateDocument(@PathVariable int id, @RequestBody UserDocument userdocument) {
        return documentService.updateDocument(id, userdocument.getDocument(),userdocument.getUser());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Document getDocumentbyId(@PathVariable int id) {
        return documentService.getDocumentById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Document createUserDocument(@RequestBody UserDocument document) {
        return documentService.createUserDocument(document);
    }

    @RequestMapping(value = "/byUser/{id}", method = RequestMethod.GET)
    public List<Document> getAllDocuments(@PathVariable int id) {
        return documentService.getAllDocumentsByUser(id);
    }

    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.PUT)
    public Document updateDocumentStatus(@PathVariable int id, @RequestBody DocumentStatus documentStatus, @RequestBody User user) {
        return documentService.updateDocumentStatus(id, documentStatus, user);
    }

    @RequestMapping(value = "/createFlux", method = RequestMethod.POST)
    public DocumentFluxResponse createDocumentFlux(@RequestBody DocumentFluxResponse documentFlux) {
        return documentService.createDocumentFlux(documentFlux.getDocuments(), documentFlux.getGroups());
    }

    @RequestMapping(value = "/flux", method = RequestMethod.GET)
    public List<DocumentFluxResponse> getAllDocumentFluxes() {
        return documentService.getAllDocumentFluxes();

    }
    @RequestMapping(value = "/flux/{userid}", method = RequestMethod.GET)
    public List<Document> getDocumentFluxesByUserId(@PathVariable int userid) {
        return documentService.getDocumentFluxByUserId(userid);
    }

    @RequestMapping(value = "/activewz", method = RequestMethod.GET)
    public List<Document> getActiveWorkZone() {
        return documentService.getActizeWZDocuments();
    }

    @RequestMapping(value = "/completedwz", method = RequestMethod.GET)
    public List<Document> getCompletedWorkZone() {
        return documentService.getCompletedWZDocuments();
    }

    @RequestMapping(value = "/taskwz", method = RequestMethod.GET)
    public List<Document> getTaskWorkZone() {
        return documentService.getTaskWZDocuments();
    }

    @RequestMapping(value = "/workzone", method = RequestMethod.GET)
    public List<Document> getWorkZone() {
        return documentService.getWorkZoneDocuments();
    }

    @RequestMapping(value = "/flux/accept", method = RequestMethod.POST)
    public Document accept(@RequestBody UserDocument document){
        return documentService.acceptDocumentflux(document);
    }

    @RequestMapping(value = "/flux/deny", method = RequestMethod.POST)
    public Document deny(@RequestBody UserDocument document){
        return documentService.denyDocumentFlux(document);
    }

}