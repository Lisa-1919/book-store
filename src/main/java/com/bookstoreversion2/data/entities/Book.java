package com.bookstoreversion2.data.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "genre")
    private String genre;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "free_book_excerpt_url")
    private String freeBookExcerptURL;
    @Column(name = "e_book_url")
    private String EBookURL;
    @Column(name = "book_type")
    private String bookType;
    @Column(name = "cover_type")
    private String bookCoverType;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "sales_number")
    private int salesNumber;
    @Column(name = "stock_quantity")
    private int stockQuantity;

    //What is it?
    @OneToMany
    @JoinTable(name = "book_images", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "image"))
    private Set<BookImage> images;

    public Book() {
        images = new HashSet<>();
    }
}
