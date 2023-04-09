package com.bookstoreversion2.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "baskets")
public class Basket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
//    @OneToMany(mappedBy = "baskets", cascade = CascadeType.ALL)
//    @JoinTable(name = "book_in_basket",  joinColumns = @JoinColumn(name = "basket_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
//    private List<BookInBasket> productsInBasket;
    @ManyToMany
    @JoinTable(name = "books_baskets", joinColumns = @JoinColumn(name = "basket_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Set<Book> books;
    @Column(name="total_price")
    private double totalPrice;
    public Basket() {
        this.books = new HashSet<>();
    }

    public Basket(Long id, User user, HashSet<Book> productsInBasket) {
        this.id = id;
        this.user = user;
        this.books = productsInBasket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Book> getProductsInBasket() {
        return books;
    }

    public void setProductsInBasket(HashSet<Book> productsInBasket) {
        this.books = productsInBasket;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
