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
    private String address;

    @ManyToMany
    @JoinTable(name = "order_details",  joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> booksInOrder;
    private double cost;
    private LocalDateTime orderDate;
    private LocalDateTime receiptDate;
    private Status status;
    private PaymentMethod paymentMethod;
    private ShippingMethod shippingMethod;

    public Order() {
    }

    public Order(Long id, User user, String address, List<Book> booksInOrder, double cost, LocalDateTime orderDate, LocalDateTime receiptDate, Status status, PaymentMethod paymentMethod, ShippingMethod shippingMethod) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.booksInOrder = booksInOrder;
        this.cost = cost;
        this.orderDate = orderDate;
        this.receiptDate = receiptDate;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.shippingMethod = shippingMethod;
    }
}
