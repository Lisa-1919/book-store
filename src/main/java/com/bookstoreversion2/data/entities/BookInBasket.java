package com.bookstoreversion2.data.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@Table(name = "book_in_basket")
public class BookInBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private Basket basket;
    @Column(name = "quantity")
    private int quantity;

    public BookInBasket() {
    }

    public BookInBasket(Long id, Book book, Basket basket, int quantity) {
        this.id = id;
        this.book = book;
        this.basket = basket;
        this.quantity = quantity;
    }

    public BookInBasket(Basket basket, Book book, int quantity){
        this.basket = basket;
        this.book = book;
        this.quantity = quantity;
    }

}
