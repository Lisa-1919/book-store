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

}
