package com.bookstoreversion2.controllers;

import com.bookstoreversion2.data.entities.*;
import com.bookstoreversion2.services.*;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @Autowired
    private RatingService ratingService;

    @ModelAttribute("basket")
    public Basket getBasket() {
        return this.userServiceImp.getAuthorizedUserBasket();
    }
    @ModelAttribute("order")
    public Order getOrder() {
        return new Order();
    }
    @ModelAttribute("book")
    public Book getBook(@PathVariable("id") Long id) {
        return bookServiceImp.getBookById(id);
    }
    @ModelAttribute("orders")
    public List<Order> getAllOrders(){
        return this.orderServiceImp.getAllOrdersByUserId(userServiceImp.getAuthorizedUser().getId());
    }

    @GetMapping("/basket")
    public String basketPage(Model model) {
        return "basket";
    }

    @GetMapping("/orders")
    public String viewAllOrders(Model model){
        return "order_list_page";
    }

    @GetMapping("/book/{id}")
    public String bookPage(@PathVariable("id") Long id, Model model) {
        Book book = bookServiceImp.getBookById(id);
        model.addAttribute("book", book);
        return "book_page";
    }

    @PostMapping("/book/{id}")
    public String rating(@RequestParam("rating") double rating, Model model) {
        ratingService.add(userServiceImp.getAuthorizedUser(), (Book) model.getAttribute("book"), rating);
        return "book_page";
    }



    @GetMapping("/book/{id}/add")
    public String addToBasket(@PathVariable("id") Long id, Model model) {
        basketServiceImp.addProductToBasket(id);
        return "redirect:/catalog";
    }

    @PostMapping("/book/delete")
    public String deleteFromBasket(@RequestParam("selectedBooks") List<Long> selectedBooks, Model model) {
//        Book book = bookServiceImp.getBookById(id);

        //      basketServiceImp.deleteProductsFromBasket(book);
        return "redirect:/catalog";
    }

    @PostMapping("/basket/order/create")
    public String createOrder(@ModelAttribute("order") Order order, @RequestParam("selectedBooks") List<Long> selectedBooks, Model model) {
        List<BookInBasket> toOrder = new ArrayList<>();
        Basket basket = userServiceImp.getAuthorizedUserBasket();
        for (Long selectedBookId : selectedBooks) {
            for (BookInBasket bookInBasket : basket.getBooks()) {
                if (selectedBookId == bookInBasket.getId()) {
                    toOrder.add(bookInBasket);
                }
            }
        }
        orderServiceImp.createNewOrder(order, toOrder);
        return "redirect:/catalog";
    }
}
