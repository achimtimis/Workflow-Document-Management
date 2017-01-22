package com.proiectcolectiv.models.workzones;

import com.proiectcolectiv.models.document.Document;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by achy_ on 1/1/2017.
 */
@Data
@Entity
@Table(name = "completedwz")
public class CompletedWorkZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Document completedDocuments;

}
