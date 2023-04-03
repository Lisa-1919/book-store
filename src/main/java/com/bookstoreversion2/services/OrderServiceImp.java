package com.bookstoreversion2.services;

import com.bookstoreversion2.entities.Order;
import com.bookstoreversion2.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

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
    public void createNewOrder(Order order) {
        orderRepository.save(order);
    }
}
