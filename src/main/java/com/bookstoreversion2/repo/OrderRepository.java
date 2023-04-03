package com.bookstoreversion2.repo;

import com.bookstoreversion2.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
