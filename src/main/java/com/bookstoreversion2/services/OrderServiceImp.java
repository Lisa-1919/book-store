package com.bookstoreversion2.services;

import com.bookstoreversion2.data.entities.*;
import com.bookstoreversion2.data.repo.BasketRepository;
import com.bookstoreversion2.data.repo.BookInBasketRepository;
import com.bookstoreversion2.data.repo.BookRepository;
import com.bookstoreversion2.data.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private BasketRepository basketRepository;

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> getAllOrdersByUserId(Long userId) {
        List<Order> userOrderList = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            if (order.getUser().getId().equals(userId))
                userOrderList.add(order);
        }
        return userOrderList;
    }


    @Override
    public void createNewOrder(Order order, List<BookInBasket> books) {
        double totalCost = 0;
        for (BookInBasket bookInBasket : books) {
            totalCost += bookInBasket.getQuantity() * bookInBasket.getBook().getPrice();
            bookInBasket.getBook().setSalesNumber(bookInBasket.getBook().getSalesNumber() + bookInBasket.getQuantity());
            bookRepository.save(bookInBasket.getBook());
        }
        order.setCost(totalCost);
        Basket basket = userServiceImp.getAuthorizedUserBasket();
        order.setOrderDate(LocalDateTime.now());
        order.setUser(userServiceImp.getAuthorizedUser());
        order.setStatus(Status.PROCESSING.getStatusName());
        List<Book> list = new ArrayList<>();
        books.forEach(bookInBasket -> list.add(bookInBasket.getBook()));
        order.setBooksInOrder(list);
        orderRepository.save(order);
        if (basket.getBooks().removeAll(books)) {
            basket.setTotalPrice(basket.getTotalPrice() - totalCost);
            basketRepository.save(basket);
        }
    }
}
