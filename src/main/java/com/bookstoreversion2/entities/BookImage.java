package com.bookstoreversion2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "book_images")
public class BookImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  //  @Column(name = "image")
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public BookImage() {
    }

    public BookImage(Long id, String imageURL, Book book) {
        this.id = id;
        this.imageURL = imageURL;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Book getProduct() {
        return book;
    }

    public void setProduct(Book book) {
        this.book = book;
    }
}