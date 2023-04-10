package com.bookstoreversion2.data.repo;

import com.bookstoreversion2.data.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
