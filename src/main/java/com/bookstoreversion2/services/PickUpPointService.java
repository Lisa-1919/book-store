package com.bookstoreversion2.services;

import com.bookstoreversion2.data.entities.PickUpPoint;

import java.util.List;

public interface PickUpPointService {

    boolean save(PickUpPoint pickUpPoint);
    boolean update(PickUpPoint pickUpPoint);
    void delete(PickUpPoint pickUpPoint);
    PickUpPoint findByAddress(String address);
    PickUpPoint findById(Long id);
    List<PickUpPoint> findAll();
    void ratePickUpPoint(Long id, double rating);
}
