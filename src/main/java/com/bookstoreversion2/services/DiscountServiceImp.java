package com.bookstoreversion2.services;

import com.bookstoreversion2.data.entities.Book;
import com.bookstoreversion2.data.entities.Discount;
import com.bookstoreversion2.data.repo.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImp implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public void save(Discount discount) {
        discountRepository.save(discount);
    }

    @Override
    public void delete(Discount discount) {
        discountRepository.delete(discount);
    }

    @Override
    public Discount findById(Long id) {
        Discount discount = discountRepository.findById(id).get();
        return discount;
    }

    @Override
    public void update(Discount discount) {
        Discount discountInDb = discountRepository.findById(discount.getId()).get();
        discountInDb.setTitle(discount.getTitle());
        discountInDb.setDescription(discount.getDescription());
        discountInDb.setStartDate(discount.getStartDate());
        discountInDb.setEndDate(discount.getEndDate());
        discountInDb.setParameter(discount.getParameter());
        discountInDb.setValue(discount.getValue());
        discountInDb.setDiscountAmount(discountInDb.getDiscountAmount());
        discountRepository.save(discountInDb);
    }

    @Override
    public List<Discount> getAllDiscounts() {
        List<Discount> discounts = discountRepository.findAll();
        return discounts;
    }

}
