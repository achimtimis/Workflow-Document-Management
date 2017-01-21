package com.proiectcolectiv.models.document;

import com.proiectcolectiv.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by achy_ on 1/21/2017.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserDocument {
    private Document document;
    private User user;

    public UserDocument(Document document, User user) {
        this.document = document;
        this.user = user;
    }
}
