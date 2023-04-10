package com.bookstoreversion2.controllers;

import com.bookstoreversion2.data.entities.Basket;
import com.bookstoreversion2.data.entities.Book;
import com.bookstoreversion2.data.entities.User;
import com.bookstoreversion2.services.BasketServiceImp;
import com.bookstoreversion2.services.BookServiceImp;
import com.bookstoreversion2.services.OrderServiceImp;
import com.bookstoreversion2.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/basket")
    public String basketPage(Model model){
        User user = userServiceImp.getAuthorizedUser();
        Basket basket = basketServiceImp.getBasketByUserId(user.getId());
        model.addAttribute("books", basket.getProductsInBasket());
        model.addAttribute("totalPrice", basket.getTotalPrice());
        List<Book> list = new ArrayList<>();
        model.addAttribute("list", list);
        boolean add = false;
        model.addAttribute("add", add);
        return "basket";
    }

    @GetMapping("/book/{id}")
    public String bookPage(@PathVariable("id") Long id, Model model){
        Book book = bookServiceImp.getBookById(id);
        model.addAttribute("book", book);
        return "book_page";
    }

    @GetMapping("/book/{id}/add")
    public String addToBasket(@PathVariable("id") Long id, Model model){
        basketServiceImp.addProductToBasket(id);
        return "redirect:/catalog";
    }

    @PostMapping("/book/{id}/delete")
    public String deleteFromBasket(@PathVariable("id") Long id, Model model){
        Book book = bookServiceImp.getBookById(id);
        basketServiceImp.deleteProductsFromBasket(book);
        return "redirect:/catalog";
    }

    @PostMapping("/user/book/order/create")
    public String createOrder(@ModelAttribute("books") List<Book> books, Model model){
       // List<Book> books = (List<Book>) model.getAttribute("list");
        orderServiceImp.createNewOrder(books);
        return "redirect:/catalog";
    }
}
