package com.bookstoreversion2.controllers;

import com.bookstoreversion2.entities.User;
import com.bookstoreversion2.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {

    @Autowired
    private UserServiceImp userServiceImp;
    public StoreController() {
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        return "home";
    }
}
