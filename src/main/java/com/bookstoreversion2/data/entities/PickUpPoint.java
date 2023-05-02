package com.bookstoreversion2.data.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Builder
@Getter
@Setter
@Entity
@Table(name = "pick_up_points")
public class PickUpPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "rating")
    private double rating;
    @Column(name="work_schedule")
    private String workSchedule;
    @Column(name = "start_of_business")
    private LocalTime startOfBusiness;
    @Column(name = "close_of_business")
    private LocalTime closeOfBusiness;

    public PickUpPoint() {
    }

    public PickUpPoint(Long id, String address, double rating, String workSchedule, LocalTime startOfBusiness, LocalTime closeOfBusiness) {
        this.id = id;
        this.address = address;
        this.rating = rating;
        this.workSchedule = workSchedule;
        this.startOfBusiness = startOfBusiness;
        this.closeOfBusiness = closeOfBusiness;
    }

}
