package com.bookstoreversion2.services;

import com.bookstoreversion2.entities.Basket;
import com.bookstoreversion2.entities.Book;

import java.util.List;

public interface BasketService {

    Basket getBasketById(Long id);
    Basket getBasketByUserId(Long userId);
    void addProductToBasket(Long basketId, Book book, int quantity);
    void deleteProductsFromBasket(Long basketId, List<Book> books);
    void clearBasket(Long id);

}