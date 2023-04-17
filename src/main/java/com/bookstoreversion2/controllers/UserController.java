package com.bookstoreversion2.controllers;

import com.bookstoreversion2.data.entities.Basket;
import com.bookstoreversion2.data.entities.Book;
import com.bookstoreversion2.data.entities.BookInBasket;
import com.bookstoreversion2.data.entities.User;
import com.bookstoreversion2.services.BasketServiceImp;
import com.bookstoreversion2.services.BookServiceImp;
import com.bookstoreversion2.services.OrderServiceImp;
import com.bookstoreversion2.services.UserServiceImp;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private BookServiceImp bookServiceImp;
    @Autowired
    private BasketServiceImp basketServiceImp;

    @Autowired
    private OrderServiceImp orderServiceImp;

    @ModelAttribute("basket")
    public Basket getBasket(){
        return this.userServiceImp.getAuthorizedUserBasket();
    }

    @GetMapping("/basket")
    public String basketPage(Model model) {
        User user = userServiceImp.getAuthorizedUser();
   //     Basket basket = basketServiceImp.getBasketByUserId(user.getId());
 //       model.addAttribute("basket", basket);
        return "basket";
    }

    @GetMapping("/book/{id}")
    public String bookPage(@PathVariable("id") Long id, Model model) {
        Book book = bookServiceImp.getBookById(id);
        model.addAttribute("book", book);
        return "book_page";
    }

    @GetMapping("/book/{id}/add")
    public String addToBasket(@PathVariable("id") Long id, Model model) {
        basketServiceImp.addProductToBasket(id);
        return "redirect:/catalog";
    }

    @PostMapping("/book/{id}/delete")
    public String deleteFromBasket(@PathVariable("id") Long id, Model model) {
        Book book = bookServiceImp.getBookById(id);
        basketServiceImp.deleteProductsFromBasket(book);
        return "redirect:/catalog";
    }

    @PostMapping("/basket/order/create")
    public String createOrder(@RequestParam("selectedBooks") List<Long> selectedBooks, Model model) {
        List<BookInBasket> toOrder = new ArrayList<>();
        Basket basket = userServiceImp.getAuthorizedUserBasket();
        for (Long selectedBookId : selectedBooks) {
            for(BookInBasket bookInBasket : basket.getBooks()){
                if(selectedBookId == bookInBasket.getId()){
                    toOrder.add(bookInBasket);
                }
            }
        }
        orderServiceImp.createNewOrder(toOrder);
        return "redirect:/catalog";
    }
}
