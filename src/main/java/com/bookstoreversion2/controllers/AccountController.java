package com.bookstoreversion2.controllers;

import com.bookstoreversion2.data.entities.Role;
import com.bookstoreversion2.data.entities.User;
import com.bookstoreversion2.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class AccountController {

    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountController() {
    }

    @GetMapping("/registration")
    public String registrationPage(Model model){
        return "registration";
    }

    @PostMapping("/registration")
    public String userRegistration(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phone,
                                   @RequestParam String email, @RequestParam String password,
                                   Model model){
        User user = new User(email, phone, firstName, lastName, bCryptPasswordEncoder.encode(password), Collections.singleton(new Role(3, "USER")));
        userServiceImp.createNewAccount(user);
        return "redirect:/home";
    }


    @GetMapping("/account")
    public String authenticationSuccess(Model model) {
        String roleName = userServiceImp.getRoleName();
        return switch (roleName) {
            case "ADMIN" -> "account_admin";
            case "MANAGER" -> "account_manager";
            case "USER" -> "account_user";
            default -> "home";
        };
    }

    @GetMapping("/account/password")
    public String passwordUpdatePage(Model model){
        return "password_page";
    }

    @PostMapping("/account/password")
    public String updatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Model model){
        if(bCryptPasswordEncoder.matches(oldPassword, userServiceImp.getAuthorizedUser().getPassword())) {
            userServiceImp.updatePassword(bCryptPasswordEncoder.encode(newPassword));
            return "redirect:/account";
        }else {
            return "redirect:/account/password";
        }
    }
}
