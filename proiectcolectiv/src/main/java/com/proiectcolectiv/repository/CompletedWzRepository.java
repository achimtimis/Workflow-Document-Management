package com.proiectcolectiv.repository;

import com.proiectcolectiv.models.workzones.CompletedWorkZone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by achy_ on 1/21/2017.
 */
public interface CompletedWzRepository extends JpaRepository<CompletedWorkZone,Long> {

}
