package com.bookstoreversion2.services;

import com.bookstoreversion2.data.entities.Book;
import com.bookstoreversion2.data.entities.Order;
import com.bookstoreversion2.data.repo.BasketRepository;
import com.bookstoreversion2.data.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BasketServiceImp basketServiceImp;
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
        for(Order order: orderRepository.findAll()){
            if(order.getUser().getId().equals(userId))
                userOrderList.add(order);
        }
        return userOrderList;
    }

    @Override
    public void createNewOrder(List<Book> books) {
//        Order order = new Order();
//        List<Book> toOrder = new ArrayList<>();
//        for(Book book: books){
//            if(book.isAdd())
//                toOrder.add(book);
//        }
//        order.setBooksInOrder(toOrder);
//        Basket basket = userServiceImp.getAuthorizedUserBasket();
//        if(basket.getProductsInBasket().removeAll(toOrder)) {
//            basketRepository.save(basket);
//            orderRepository.save(order);
//        }
    }
}
