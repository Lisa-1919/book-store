package com.bookstoreversion2.data.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "discount_amount")
    private double discountAmount;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "parameter")
    private String parameter;
    @Column(name = "value")
    private String value;

    public Discount() {
    }

    public Discount(Long id, double discountAmount, LocalDateTime startDate, LocalDateTime endDate, String parameter, String value) {
        this.id = id;
        this.discountAmount = discountAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.parameter = parameter;
        this.value = value;
    }
}
