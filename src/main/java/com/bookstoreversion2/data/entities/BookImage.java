package com.bookstoreversion2.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book_images")
public class BookImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image_url")
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    public BookImage() {
    }

    public BookImage(Long id, String imageURL, Book book) {
        this.id = id;
        this.imageURL = imageURL;
        this.book = book;
    }

    public BookImage(String imageURL) {
        this.imageURL = imageURL;
    }
}
