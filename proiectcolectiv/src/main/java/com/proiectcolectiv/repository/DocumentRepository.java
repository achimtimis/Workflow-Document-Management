package com.proiectcolectiv.repository;

import com.proiectcolectiv.models.document.Document;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by achy_ on 1/20/2017.
 */
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
