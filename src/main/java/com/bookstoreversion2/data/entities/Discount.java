package com.bookstoreversion2.data.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "parameter")
    private String parameter;
    @Column(name = "value")
    private String value;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    public Discount() {
    }

    public Discount(Long id, double discountAmount, LocalDate startDate, LocalDate endDate, String parameter, String value, String title, String description) {
        this.id = id;
        this.discountAmount = discountAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.parameter = parameter;
        this.value = value;
        this.title = title;
        this.description = description;
    }
}
