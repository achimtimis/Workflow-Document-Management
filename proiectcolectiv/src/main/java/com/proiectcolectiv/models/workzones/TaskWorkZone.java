package com.proiectcolectiv.models.workzones;

import com.proiectcolectiv.models.document.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by achy_ on 1/1/2017.
 */
//@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "taskwz")
public class TaskWorkZone {
    public TaskWorkZone(Document taskedDocuments) {
        this.taskedDocuments = taskedDocuments;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Document taskedDocuments;
}
