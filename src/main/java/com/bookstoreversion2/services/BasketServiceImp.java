package com.bookstoreversion2.services;


import com.bookstoreversion2.entities.Basket;
import com.bookstoreversion2.entities.Book;
import com.bookstoreversion2.entities.BookInBasket;
import com.bookstoreversion2.entities.User;
import com.bookstoreversion2.repo.BasketRepository;
import com.bookstoreversion2.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImp implements BasketService {

    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private BookRepository bookRepository;


    @Override
    public Basket getBasketById(Long id) {
        return basketRepository.findById(id).get();
    }

    @Override
    public Basket getBasketByUserId(Long userId) {
        Basket userBasket = new Basket();
        for (Basket basket : basketRepository.findAll()) {
            if (basket.getUser().getId().equals(userId))
                userBasket = basket;
        }
        return userBasket;
    }

    @Override
    public void addProductToBasket(Long bookId) {
        Basket basket = userServiceImp.getAuthorizedUserBasket();
        Book book = bookRepository.findById(bookId).get();
        double totalPrice = basket.getTotalPrice();
        if (basket.getProductsInBasket().add(book))
            totalPrice += book.getPrice();
        basket.setTotalPrice(totalPrice);
        basketRepository.save(basket);
    }

    @Override
    public void deleteProductsFromBasket(Book book) {
        Basket basket = userServiceImp.getAuthorizedUserBasket();
        if(basket.getProductsInBasket().remove(book))
            basket.setTotalPrice(basket.getTotalPrice() - book.getPrice());
        basketRepository.save(basket);
        //   basketRepository.findById(basketId).get().getProductsInBasket().removeAll(books);
    }

    @Override
    public void clearBasket(Long id) {
//        Basket basket = basketRepository.findById(id).get();
//        basket.getProductsInBasket().clear();
    }


}
