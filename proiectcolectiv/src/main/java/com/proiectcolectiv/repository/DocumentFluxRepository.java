package com.proiectcolectiv.repository;

import com.proiectcolectiv.models.document.DocumentFlux;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by achy_ on 1/21/2017.
 */
public interface DocumentFluxRepository extends JpaRepository<DocumentFlux,Long> {
    List<DocumentFlux> findByHashCode(int hashCode);
}
