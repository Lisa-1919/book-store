package com.bookstoreversion2.controllers;

import com.bookstoreversion2.data.entities.Book;
import com.bookstoreversion2.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StoreController implements ErrorController {

    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private BookServiceImp bookServiceImp;
    @Autowired
    private PickUpPointServiceImp pickUpPointServiceImp;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private DiscountServiceImp discountServiceImp;

    @GetMapping({"/home"})
    public String homePage(Model model) {
        List<Book> books = ratingService.getRecommendation();
        model.addAttribute("books", books);
        return "home";
    }


    @GetMapping("/catalog")
    public String catalogPage(@RequestParam(required = false, name = "query") String query,
                              @RequestParam(required = false, name = "sort") String sort,
                              @RequestParam(required = false, name = "genre") String genre, Model model) {
        List<Book> books;
        if (query != null) {
            books = bookServiceImp.search(query);
        } else if (sort != null) {
            books = bookServiceImp.sort(sort);
        } else if (genre != null) {
            books = bookServiceImp.genreFilter(genre);
        } else {
            books = bookServiceImp.getAllBooks();
        }
        books.forEach(book -> bookServiceImp.is(book));
        model.addAttribute("books", books);
        return "catalog";
    }

    @PostMapping("/catalog")
    public String filter(@RequestParam("min") double min, @RequestParam("max") double max, Model model){
        model.addAttribute("books", bookServiceImp.priceFilter(min, max));
        return "catalog";
    }

    @GetMapping("/pick-up-points")
    public String pickUpPointsPage(Model model) {
        model.addAttribute("pickUpPoints", pickUpPointServiceImp.findAll());
        return "pick_up_points";
    }

    @GetMapping("/discounts")
    public String discounts(Model model){
        model.addAttribute("discounts", discountServiceImp.getAllDiscounts());
        return "discounts";
    }

    @RequestMapping("/error")
    public String handleError(Model model /*HttpServletRequest request*/) {
        return "error";
    }

}
