package com.bookstoreversion2.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

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

    public Order(Long id, User user, String address, List<Book> books, double cost, LocalDateTime orderDate,
                 LocalDateTime receiptDate, Status status, PaymentMethod paymentMethod, ShippingMethod shippingMethod) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.booksInOrder = books;
        this.cost = cost;
        this.orderDate = orderDate;
        this.receiptDate = receiptDate;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.shippingMethod = shippingMethod;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooksInOrder() {
        return booksInOrder;
    }

    public void setBooksInOrder(List<Book> productsInOrder) {
        this.booksInOrder = productsInOrder;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDateTime receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }
}
