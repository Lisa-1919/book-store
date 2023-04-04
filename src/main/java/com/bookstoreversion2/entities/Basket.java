package com.bookstoreversion2.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "baskets")
public class Basket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToMany
    @JoinTable(name = "book_in_basket",  joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<BookInBasket> productsInBasket;

    public Basket() {
        this.productsInBasket = new ArrayList<>();
    }

    public Basket(Long id, User user, List<BookInBasket> productsInBasket) {
        this.id = id;
        this.user = user;
        this.productsInBasket = productsInBasket;
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

    public List<BookInBasket> getProductsInBasket() {
        return productsInBasket;
    }

    public void setProductsInBasket(List<BookInBasket> productsInBasket) {
        this.productsInBasket = productsInBasket;
    }
}
