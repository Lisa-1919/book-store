package com.bookstoreversion2.controllers;

import com.bookstoreversion2.entities.Book;
import com.bookstoreversion2.entities.Discount;
import com.bookstoreversion2.services.DiscountServiceImp;
import com.bookstoreversion2.services.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @Autowired
    private DiscountServiceImp discountServiceImp;

    public ManagerController() {
    }

    @GetMapping("/manager")
    public String managerPage(Model model){
        return "account_manager";
    }

    @GetMapping("/manager/catalog")
    public String catalogPage(Model model){
        List<Book> books = productServiceImp.getAllProducts();
        model.addAttribute("books", books);
        return "manager_catalog";
    }

    @GetMapping("/manager/book/add")
    public String addBookPage(){
        return "add_book";
    }

    @PostMapping("/manager/book/add")
    public String addBook(Model model){
        Book book = new Book();
        productServiceImp.addNewProduct(book);
        return "redirect:/manager/catalog";
    }

    @GetMapping("/manager/book/{id}/edit")
    public String bookPage(@RequestParam Long id, Model model){
        Book book = productServiceImp.getProductById(id);
        model.addAttribute("book", book);
        return "manager_book_page";
    }

    @PostMapping("/manager/book/{id}/edit")
    public String editBook(@RequestAttribute Book book, Model model){
        productServiceImp.updateProduct(book);
        return "redirect:/manager/catalog";
    }

    @GetMapping("/manager/product/{id}/delete")
    public String deleteBook(@RequestParam Book book, Model model){
        productServiceImp.deleteProduct(book);
        return "redirect:/manager/catalog";
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
