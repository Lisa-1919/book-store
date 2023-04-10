package com.bookstoreversion2.data.repo;


import com.bookstoreversion2.data.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
