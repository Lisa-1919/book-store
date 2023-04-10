package com.bookstoreversion2.services;

import com.bookstoreversion2.data.entities.Discount;

import java.util.List;

public interface DiscountService {

    void save(Discount discount);
    void delete(Discount discount);
    Discount findById(Long id);
    void update(Discount discount);

    List<Discount> getAllDiscounts();
}
