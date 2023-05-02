package com.bookstoreversion2.data.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @Column(name = "book_cover_type")
    private String bookCoverType;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "sales_number")
    private int salesNumber;
    @Column(name = "stock_quantity")
    private int stockQuantity;


    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookImage> images;
    public Book() {
        images = new ArrayList<BookImage>();
    }

    public Book(Long id, String title, String author, String genre, String publisher, String freeBookExcerptURL, String EBookURL, String bookType, String bookCoverType, String description, double price, int salesNumber, int stockQuantity, List<BookImage> images) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.freeBookExcerptURL = freeBookExcerptURL;
        this.EBookURL = EBookURL;
        this.bookType = bookType;
        this.bookCoverType = bookCoverType;
        this.description = description;
        this.price = price;
        this.salesNumber = salesNumber;
        this.stockQuantity = stockQuantity;
        this.images = images;
    }

    public String mainImgUrl(){
        String mainImgUrl = "";
        if(images.size() == 0){
            mainImgUrl = "not_found.svg";
        }
        else {
            mainImgUrl = images.get(0).getImageURL();
        }
        return mainImgUrl;
    }
}
