package com.bookstoreversion2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "book_in_basket")
public class BookInBasket extends Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long book;
    @Column(name = "basket_id")
    private Long basket;
    @Column(name = "quantity")
    private int quantity;

    public BookInBasket() {
    }

    public BookInBasket(Long id, Long book, Long basket, int quantity) {
        this.id = id;
        this.book = book;
        this.basket = basket;
        this.quantity = quantity;
    }

    public BookInBasket(Long basket, Long book, int quantity){
        this.basket = basket;
        this.book = book;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBasket() {
        return basket;
    }

    public void setBasket(Long basket) {
        this.basket = basket;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getBook() {
        return book;
    }

    public void setBook(Long book) {
        this.book = book;
    }
}
