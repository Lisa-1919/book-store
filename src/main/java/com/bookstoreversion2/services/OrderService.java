package com.bookstoreversion2.services;

import com.bookstoreversion2.data.entities.Book;
import com.bookstoreversion2.data.entities.BookInBasket;
import com.bookstoreversion2.data.entities.Order;

import java.util.List;

public interface OrderService {
    Order getOrderById(Long id);
    List<Order> getAllOrdersByUserId(Long userId);
    void createNewOrder(Order order, List<BookInBasket> book);

}
