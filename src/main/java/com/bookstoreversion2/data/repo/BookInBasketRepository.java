package com.bookstoreversion2.data.repo;

import com.bookstoreversion2.data.entities.BookInBasket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInBasketRepository extends JpaRepository<BookInBasket, Long> {
}
