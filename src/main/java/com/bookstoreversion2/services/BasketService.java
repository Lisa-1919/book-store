package com.bookstoreversion2.services;

import com.bookstoreversion2.data.entities.Basket;
import com.bookstoreversion2.data.entities.Book;
import com.bookstoreversion2.data.entities.BookInBasket;

public interface BasketService {

    Basket getBasketById(Long id);
    Basket getBasketByUserId(Long userId);
    boolean addProductToBasket(Long bookId);
    void deleteProductsFromBasket(BookInBasket bookInBasket);
    void clearBasket(Long id);

}
