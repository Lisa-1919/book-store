package com.bookstoreversion2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "book_in_basket")
public class BookInBasket extends Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookId;
    private Long basketId;
    private int quantity;

    public BookInBasket() {
    }

    public BookInBasket(Long id, Long bookId, Long basketId, int quantity) {
        this.id = id;
        this.bookId = bookId;
        this.basketId = basketId;
        this.quantity = quantity;
    }

    public BookInBasket(Long basketId, Long bookId, int quantity){
        this.basketId = basketId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long productId) {
        this.bookId = productId;
    }
}
