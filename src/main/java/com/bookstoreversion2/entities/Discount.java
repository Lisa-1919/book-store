package com.bookstoreversion2.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table()
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double discountAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Discount() {
    }

    public Discount(Long id, double discountAmount, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.discountAmount = discountAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
