package com.bookstoreversion2.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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
    private String eBookURL;
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
//    @OneToMany
//    @JoinTable(name = "book_images", joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "image"))
//    private List<BookImage> images;

    public Book() {
    }

    public Book(Long id, String title, String author, String genre, String publisher, String freeBookExcerptURL, String eBookURL, String bookType, String bookCoverType, String description, double price, int salesNumber, int stockQuantity, List<BookImage> images) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.freeBookExcerptURL = freeBookExcerptURL;
        this.eBookURL = eBookURL;
        this.bookType = bookType;
        this.bookCoverType = bookCoverType;
        this.description = description;
        this.price = price;
        this.salesNumber = salesNumber;
        this.stockQuantity = stockQuantity;
      //  this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(int salesNumber) {
        this.salesNumber = salesNumber;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getFreeBookExcerptURL() {
        return freeBookExcerptURL;
    }

    public void setFreeBookExcerptURL(String freeBookExcerptURL) {
        this.freeBookExcerptURL = freeBookExcerptURL;
    }

    public String getEBookURL() {
        return eBookURL;
    }

    public void setEBookURL(String eBookURL) {
        this.eBookURL = eBookURL;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookCoverType() {
        return bookCoverType;
    }

    public void setBookCoverType(String bookCoverType) {
        this.bookCoverType = bookCoverType;
    }

//    public List<BookImage> getImages() {
//        return new ArrayList<>(images);
//    }
//
//    public void setImages(List<BookImage> images) {
//        this.images = images;
//    }
}
