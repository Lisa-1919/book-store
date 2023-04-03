package com.bookstoreversion2.services;

import com.bookstoreversion2.entities.Order;

import java.util.List;

public interface OrderService {
    Order getOrderById(Long id);
    List<Order> getAllOrdersByUserId(Long userId);
    void createNewOrder(Order order);

}
