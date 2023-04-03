package com.bookstoreversion2.repo;


import com.bookstoreversion2.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
