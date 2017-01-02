package com.proiectcolectiv.models.workzones;

import com.proiectcolectiv.models.document.Document;
import lombok.Data;

import java.util.List;

/**
 * Created by achy_ on 1/1/2017.
 */
@Data
public class TaskWorkZone {

    private List<Document> taskedDocuments;
}
