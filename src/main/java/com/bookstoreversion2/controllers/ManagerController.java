package com.bookstoreversion2.controllers;

import com.bookstoreversion2.entities.Book;
import com.bookstoreversion2.entities.Discount;
import com.bookstoreversion2.services.DiscountServiceImp;
import com.bookstoreversion2.services.BookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    private BookServiceImp productServiceImp;

    @Autowired
    private DiscountServiceImp discountServiceImp;

    public ManagerController() {
    }

    @GetMapping("/manager")
    public String managerPage(Model model){
        return "account_manager";
    }

    @GetMapping("/manager/book/catalog")
    public String catalogPage(Model model){
        List<Book> books = productServiceImp.getAllBooks();
        model.addAttribute("books", books);
        return "manager_catalog";
    }

    @GetMapping("/manager/book/add")
    public String addBookPage(){
        return "add_book_form";
    }

    @PostMapping("/manager/book/add")
    public String addBook(@RequestParam String title, @RequestParam String author, @RequestParam
    String price , Model model){
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(Double.parseDouble(price));
        productServiceImp.addNewBook(book);
        return "redirect:/manager/book/catalog";
    }

    @GetMapping("/manager/book/{id}")
    public String bookPage(@PathVariable(name = "id") Long id, Model model){
        Book book = productServiceImp.getBookById(id);
        model.addAttribute("book", book);
        return "manager_book_page";
    }

    @PostMapping("/manager/book/{id}")
    public String editBook(@PathVariable("id") Long id,@RequestParam("title") String title, @RequestParam("author") String author,
                           @RequestParam("price") double price, Model model){
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);
        productServiceImp.updateBook(book);
        return "redirect:/manager/book/catalog";
    }

    @GetMapping("/manager/book/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id, Model model){
        productServiceImp.deleteBook(id);
        return "redirect:/manager/book/catalog";
    }


    @GetMapping("/manager/discounts")
    public String discountsPage(Model model){
        return "discounts_page";
    }

    @GetMapping("/manager/discounts/add")
    public String addDiscountPage(Model model){
        return "add_discount_page";
    }

    @PostMapping("/manager/discounts/add")
    public String addDiscount(@RequestAttribute Discount discount, Model model){
        discountServiceImp.save(discount);
        return "redirect:/manager/discounts";
    }

    @GetMapping("/manager/discounts/{id}")
    public String discountPage(@RequestParam Long id, Model model){
        Discount discount = discountServiceImp.findById(id);
        model.addAttribute("discount", discount);
        return "discount_page";
    }

    @PostMapping("/manager/discounts/{id}/edit")
    public String editDiscount(@RequestAttribute Discount discount, Model model){
        discountServiceImp.update(discount);
        return "redirect:/manager/discounts";
    }


}
