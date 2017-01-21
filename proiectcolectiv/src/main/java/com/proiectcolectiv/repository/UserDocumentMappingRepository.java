package com.proiectcolectiv.repository;

import com.proiectcolectiv.models.document.UserDocumentMapping;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by achy_ on 1/21/2017.
 */
public interface UserDocumentMappingRepository extends JpaRepository<UserDocumentMapping, Long> {
}
