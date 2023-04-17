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
@Table(name = "baskets")
public class Basket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookInBasket> books;
    @Column(name="total_price")
    private double totalPrice;
    public Basket() {
        this.books = new ArrayList<>();
    }

    public Basket(Long id, User user, List<BookInBasket> books, double totalPrice) {
        this.id = id;
        this.user = user;
        this.books = books;
        this.totalPrice = totalPrice;
    }

}
