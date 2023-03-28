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
    private String name;
    private String author;
    private String genre;
    private String publisher;
    private String freeBookExcerptURL;
    private String eBookURL;
    private String bookType;
    private String bookCoverType;
    private String description;
    private double price;
    private int salesNumber;
    private int stockQuantity;
    @OneToMany
    @JoinTable(name = "book_images", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "image"))
    private List<ProductImage> images;

    public Book() {
    }

    public Book(Long id, String name, String author, String genre, String publisher, String freeBookExcerptURL, String eBookURL, String bookType, String bookCoverType, String description, double price, int salesNumber, int stockQuantity, List<ProductImage> images) {
        this.id = id;
        this.name = name;
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
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<ProductImage> getImages() {
        return new ArrayList<>(images);
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }
}
