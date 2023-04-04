package com.bookstoreversion2.services;


import com.bookstoreversion2.entities.Basket;
import com.bookstoreversion2.entities.Book;
import com.bookstoreversion2.entities.BookInBasket;
import com.bookstoreversion2.repo.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImp implements BasketService {

    private final BasketRepository basketRepository;

    public BasketServiceImp(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

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
    public void addProductToBasket(Long basketId, Book book, int quantity) {
        Basket basket = basketRepository.findById(basketId).get();
        for (BookInBasket bookInBasket : basket.getProductsInBasket()) {
            if (bookInBasket.getBook().equals(book.getId()))
                basket.getProductsInBasket().add(new BookInBasket(basket.getId(), book.getId(), quantity));
            else
                bookInBasket.setQuantity(bookInBasket.getQuantity() + quantity);
        }
    }

    @Override
    public void deleteProductsFromBasket(Long basketId, List<Book> books) {
        basketRepository.findById(basketId).get().getProductsInBasket().removeAll(books);
    }

    @Override
    public void clearBasket(Long id) {
        Basket basket = basketRepository.findById(id).get();
        basket.getProductsInBasket().clear();
    }


}
