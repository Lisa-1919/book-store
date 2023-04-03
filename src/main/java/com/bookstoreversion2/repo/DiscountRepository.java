package com.bookstoreversion2.repo;

import com.bookstoreversion2.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
