package com.bookstoreversion2.data.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "address")
    private String address;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_details",  joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<Book> booksInOrder;
    @Column(name = "cost")
    private double cost;
    @Column(name = "date_of_order")
    private LocalDateTime orderDate;
    @Column(name = "date_of_receipt")
    private LocalDateTime receiptDate;
    @Column(name = "status")
    private String status;

    public Order() {
    }

    public Order(Long id, User user, String address, List<Book> booksInOrder, double cost, LocalDateTime orderDate, LocalDateTime receiptDate, String status) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.booksInOrder = booksInOrder;
        this.cost = cost;
        this.orderDate = orderDate;
        this.receiptDate = receiptDate;
        this.status = status;
    }
}
