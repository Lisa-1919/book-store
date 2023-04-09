package com.bookstoreversion2.controllers;

import com.bookstoreversion2.entities.Book;
import com.bookstoreversion2.services.BookServiceImp;
import com.bookstoreversion2.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StoreController implements ErrorController {

    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private BookServiceImp bookServiceImp;

    @GetMapping({"/home", "/", ""})
    public String homePage(Model model) {
        return "home";
    }

    @GetMapping("/catalog")
    public String catalogPage(@RequestParam(required = false, name = "query") String query,
                              @RequestParam(required = false, name = "filter") String filter,
                              @RequestParam(required = false, name = "sort") String sort, Model model) {
        List<Book> books;
        if (query != null) {
            books = bookServiceImp.search(query);
        } else if (sort != null) {
            books = bookServiceImp.sort(sort);
        } else {
            books = bookServiceImp.getAllBooks();
        }
        model.addAttribute("books", books);
        return "catalog";
    }

    @RequestMapping("/error")
    public String handleError(Model model /*HttpServletRequest request*/) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//        if (status != null) {
//            Integer statusCode = Integer.valueOf(status.toString());
//
//            if(statusCode == HttpStatus.NOT_FOUND.value()) {
//                return "error-404";
//            }
//            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                return "error-500";
//            }
//        }
        return "error";
    }

}
