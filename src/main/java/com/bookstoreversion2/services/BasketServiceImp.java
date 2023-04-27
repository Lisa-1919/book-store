package com.bookstoreversion2.services;


import com.bookstoreversion2.data.entities.Basket;
import com.bookstoreversion2.data.entities.Book;
import com.bookstoreversion2.data.entities.BookInBasket;
import com.bookstoreversion2.data.repo.BasketRepository;
import com.bookstoreversion2.data.repo.BookInBasketRepository;
import com.bookstoreversion2.data.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImp implements BasketService {

    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookInBasketRepository bookInBasketRepository;


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
    public boolean addProductToBasket(Long bookId) {
        Basket basket = userServiceImp.getAuthorizedUserBasket();
        Book book = bookRepository.findById(bookId).get();
        if (isBookInBasket(basket, book)) {
            return false;
        } else {
            double totalPrice = basket.getTotalPrice();
            BookInBasket bookInBasket = BookInBasket.builder()
                    .book(book)
                    .basket(basket)
                    .quantity(1)
                    .build();
            basket.getBooks().add(bookInBasket);
            totalPrice += book.getPrice();
            basket.setTotalPrice(totalPrice);
            basketRepository.save(basket);
            return true;
        }
    }

    public boolean isBookInBasket(Basket basket, Book book){
        for(BookInBasket bookInBasket : basket.getBooks()){
            if(bookInBasket.getBook().getId() == book.getId()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteProductsFromBasket(BookInBasket bookInBasket) {
        Basket basket = userServiceImp.getAuthorizedUserBasket();
        if(basket.getBooks().remove(bookInBasket)) {
            basket.setTotalPrice(basket.getTotalPrice() - bookInBasket.getBook().getPrice()*bookInBasket.getQuantity());
        }
        basketRepository.save(basket);
    }

    @Override
    public void clearBasket(Long id) {
    }


}
