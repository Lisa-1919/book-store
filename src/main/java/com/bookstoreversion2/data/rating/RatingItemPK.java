package com.bookstoreversion2.data.rating;

import com.bookstoreversion2.data.entities.Book;
import com.bookstoreversion2.data.entities.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Embeddable
public class RatingItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name="book_id", referencedColumnName = "id")
    private Book book;

    public RatingItemPK(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public RatingItemPK() {

    }
}
