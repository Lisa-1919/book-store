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

    public Basket(Long id, User user, Set<Book> books, double totalPrice) {
        this.id = id;
        this.user = user;
        this.books = books;
        this.totalPrice = totalPrice;
    }
}
