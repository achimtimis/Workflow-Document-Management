package com.proiectcolectiv.service;

import com.proiectcolectiv.models.document.*;
import com.proiectcolectiv.models.user.User;
import com.proiectcolectiv.models.user.UserGroup;
import com.proiectcolectiv.models.workzones.ActiveWorkZone;
import com.proiectcolectiv.models.workzones.CompletedWorkZone;
import com.proiectcolectiv.models.workzones.TaskWorkZone;
import com.proiectcolectiv.models.workzones.WorkZone;
import com.proiectcolectiv.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
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

    @Autowired
    private UserService userService;

    @Autowired
    private DocumentFluxRepository documentFluxRepository;
    @Autowired
    private ActiveWzRepository activeWzRepository;
    @Autowired
    private CompletedWzRepository completedWzRepository;
    @Autowired
    private TaskwzRepository taskwzRepository;
    @Autowired
    private WorkZoneRepository workZoneRepository;

    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    public Document createUserDocument(UserDocument document) {
        Document document1 = document.getDocument();
        document1.setVersion("1");
        document1.setStatus(DocumentStatus.DRAFT);
        document1.setAuthor(document.getUser().getUsername());
        document1.setCreationDate(new Date().toString());
        document1.setLastEditedBy(document.getUser().getUsername());
        document1.setLastEditedOn(new Date().toString());
        Document result = documentRepository.save(document.getDocument());
        try{
            PrintWriter writer = new PrintWriter(document1.getName() + ".txt", "UTF-8");
            writer.println(document1.toString());
            writer.close();
        } catch (IOException e) {
            // do something
        }
//        userDocumentMappingRepository.save(new UserDocumentMapping(document.getUser(), result));
        taskwzRepository.save(new TaskWorkZone(result));
        return result;
    }

    public Document getDocumentById(int id) {
        return documentRepository.getOne(Long.valueOf(id));
    }

    public Document updateDocument(int id, Document document, User user) {
        Document found = documentRepository.findOne(Long.valueOf(id));
        Document newDocument = new Document(found.getVersion(), found.getAuthor(), found.getCreationDate(),
                found.getAbstractText(), found.getKeywords(), found.getLastEditedOn(), found.getLastEditedBy(),
                found.getName(), found.getDetails(), found.getDocumentType(), found.getStatus());
        if (found != null) {
            documentRepository.save(newDocument);
            document.setLastEditedOn(new Date().toString());
            document.setLastEditedBy(user.getUsername());
            int version = Integer.parseInt(found.getVersion());
            version++;
            document.setVersion(String.valueOf(version));
            userDocumentMappingRepository.save(new UserDocumentMapping(user, document));
            return documentRepository.save(document);
        }
        return null;
    }

    public void deleteDocumentById(int id) {
        Document found = documentRepository.getOne(Long.valueOf(id));
        List<UserDocumentMapping> userDocumentMappings = userDocumentMappingRepository.findAll();
        for (UserDocumentMapping u : userDocumentMappings) {
            if (u.getDocument().getId() == found.getId()) {
                userDocumentMappingRepository.delete(u);
            }
        }
        for (TaskWorkZone taskWorkZone : taskwzRepository.findAll()) {
            {
                if (taskWorkZone.getTaskedDocuments().getId() == found.getId()) {
                    taskwzRepository.delete(taskWorkZone);
                }
            }
        }
        for (ActiveWorkZone taskWorkZone : activeWzRepository.findAll()) {
            {
                if (taskWorkZone.getActiveDocument().getId() == found.getId()) {
                    activeWzRepository.delete(taskWorkZone);
                }
            }
        }
        for (CompletedWorkZone taskWorkZone : completedWzRepository.findAll()) {
            {
                if (taskWorkZone.getCompletedDocuments().getId() == found.getId()) {
                    completedWzRepository.delete(taskWorkZone);
                }
            }
        }
        for (WorkZone taskWorkZone : workZoneRepository.findAll()) {
            {
                if (taskWorkZone.getTodoDocuments().getId() == found.getId()) {
                    workZoneRepository.delete(taskWorkZone);
                }
            }
        }
        for (DocumentFlux dc : documentFluxRepository.findAll()) {

            if (dc.getDocument().getId() == found.getId()) {
                documentFluxRepository.delete(dc);
            }
        }

        documentRepository.delete(found);
    }


    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public List<Document> getAllDocumentsByUser(int id) {
        List<Document> result = new ArrayList<>();
        List<UserDocumentMapping> list = userDocumentMappingRepository.findAll();
        for (UserDocumentMapping m : list) {
            if (m.getUser().getId() == id) {
                result.add(documentRepository.getOne(m.getDocument().getId()));
            }
        }
        return result;
    }

    public Document updateDocumentStatus(int id, DocumentStatus status, User user) {
        Document document = documentRepository.findOne(Long.valueOf(id));
        Long documentId = document.getId();
        document.setId(null);
        documentRepository.save(document);
        document.setStatus(status);
        document.setLastEditedOn(new Date().toString());
        document.setLastEditedBy(user.getUsername());
        document.setId(documentId);
        userDocumentMappingRepository.save(new UserDocumentMapping(user, document));
        return documentRepository.save(document);

    }

    public DocumentFluxResponse createDocumentFlux(List<Document> documents, List<UserGroup> userGroups) {
        List<DocumentFlux> users = new ArrayList<>();
        int hashCode = 0;
        for (Document d : documents) {
            for (UserGroup u : userGroups) {
                hashCode = hashCode + d.hashCode() + u.hashCode();
            }
        }
        hashCode = hashCode / 13;
        for (Document d : documents) {
            for (UserGroup u : userGroups) {
                documentFluxRepository.save(new DocumentFlux(d, u, hashCode));
                for (User user : u.getUsers()) {
                    userDocumentMappingRepository.save(new UserDocumentMapping(user, d));
                }
            }
        }

        return null;
    }


    public List<DocumentFluxResponse> getAllDocumentFluxes() {
        List<Integer> hashCodes = new ArrayList<>();
        List<DocumentFluxResponse> resultt = new ArrayList<>();
        for (DocumentFlux d : documentFluxRepository.findAll()) {
            if (!hashCodes.contains(d.getHashCode())) {
                hashCodes.add(d.getHashCode());
            }
        }

        for (int el : hashCodes) {
            List<DocumentFlux> documentFluxes = documentFluxRepository.findByHashCode(el);
            List<UserGroup> userGroups = new ArrayList<>();
            List<Document> documents = new ArrayList<>();
            documents.add(documentFluxes.get(0).getDocument());
            for (DocumentFlux f : documentFluxes) {
                userGroups.add(f.getGroup());

            }
            resultt.add(new DocumentFluxResponse(documents, userGroups));
        }
        return resultt;
    }

    public List<Document> getActizeWZDocuments() {
        List<Document> result = new ArrayList<>();
        activeWzRepository.findAll().stream().forEach(d -> result.add(d.getActiveDocument()));
        return result;
    }

    public List<Document> getCompletedWZDocuments() {
        List<Document> result = new ArrayList<>();
        completedWzRepository.findAll().stream().forEach(d -> result.add(d.getCompletedDocuments()));
        return result;
    }

    public List<Document> getTaskWZDocuments() {
        List<Document> result = new ArrayList<>();
        taskwzRepository.findAll().stream().forEach(d -> result.add(d.getTaskedDocuments()));
        return result;
    }

    public List<Document> getWorkZoneDocuments() {
        List<Document> result = new ArrayList<>();
        workZoneRepository.findAll().stream().forEach(d -> result.add(d.getTodoDocuments()));
        return result;
    }

    public List<Document> getDocumentFluxByUserId(int userid) {
        List<Document> result = new ArrayList<>();
        for (UserDocumentMapping u : userDocumentMappingRepository.findAll()) {
            if (u.getUser().getId() == userid) {
                result.add(u.getDocument());
            }
        }
        return result;
    }

    public Document acceptDocumentflux(UserDocument document) {
        String group = userService.getUserGroupByUserId(document.getUser().getId());
        List<UserGroup> users = userService.findByGroup(group);
        UserGroup userGroup = users.get(0);
        Document found = document.getDocument();
        for (User u : userGroup.getUsers()){
            deleteUserDocumentMappingByUserId(u.getId());
        }

        Document newDocument = new Document(found.getVersion(), found.getAuthor(), found.getCreationDate(),
                found.getAbstractText(), found.getKeywords(), found.getLastEditedOn(), found.getLastEditedBy(),
                found.getName(), found.getDetails(), found.getDocumentType(), found.getStatus());
        documentRepository.save(newDocument);
        document.getDocument().setLastEditedBy(document.getUser().getUsername());
        document.getDocument().setLastEditedOn(new Date().toString());
        int version = Integer.parseInt(document.getDocument().getVersion());
        version++;
        document.getDocument().setVersion(String.valueOf(version));
        return documentRepository.save(document.getDocument());

    }

    private void deleteUserDocumentMappingByUserId(Long id) {
        for (UserDocumentMapping u : userDocumentMappingRepository.findAll()){
            if (u.getUser().getId() ==id){
                userDocumentMappingRepository.delete(u);
            }
        }
    }

    public Document denyDocumentFlux(UserDocument document) {
        String group = userService.getUserGroupByUserId(document.getUser().getId());
        List<UserGroup> users = userService.findByGroup(group);
        UserGroup userGroup = users.get(0);
        Document found = documentRepository.findOne(Long.valueOf(document.getDocument().getId()));
        for (User u : userGroup.getUsers()){
            deleteUserDocumentMappingByUserId(u.getId());
        }
        Document newDocument = new Document(found.getVersion(), found.getAuthor(), found.getCreationDate(),
                found.getAbstractText(), found.getKeywords(), found.getLastEditedOn(), found.getLastEditedBy(),
                found.getName(), found.getDetails(), found.getDocumentType(), found.getStatus());
        documentRepository.save(newDocument);
        document.getDocument().setLastEditedBy(document.getUser().getUsername());
        document.getDocument().setLastEditedOn(new Date().toString());
        int version = Integer.parseInt(document.getDocument().getVersion());
        version++;
        document.getDocument().setVersion(String.valueOf(version));
        document.getDocument().setStatus(DocumentStatus.DRAFT);
        return documentRepository.save(document.getDocument());
    }
}
