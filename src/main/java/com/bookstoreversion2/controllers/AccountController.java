package com.bookstoreversion2.controllers;

import com.bookstoreversion2.data.entities.Role;
import com.bookstoreversion2.data.entities.User;
import com.bookstoreversion2.services.OrderServiceImp;
import com.bookstoreversion2.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class AccountController {

    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private OrderServiceImp orderServiceImp;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountController() {
    }

    @ModelAttribute("u")
    public User getAccount() {
        return this.userServiceImp.getAuthorizedUser();
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("password", "");
        model.addAttribute("confirmPassword", "");
        return "registration";
    }

    @PostMapping("/registration")
    public String userRegistration(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phone,
                                   @RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword,
                                   Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Пароли не совпадают!");
            return "registration";
        } else if (userServiceImp.getUserByEmail(email) != null) {
            model.addAttribute("error", "Пользователь с таким email уже есть");
            return "registration";
        } else {
            User user = User.builder().username(email).phone(phone).firstName(firstName).lastName(lastName)
                    .password(bCryptPasswordEncoder.encode(password)).roles(Collections.singleton(new Role(3, "USER")))
                    .build();
            userServiceImp.createNewAccount(user);
            return "redirect:/home";
        }
    }


    @GetMapping("/account")
    public String authenticationSuccess(Model model) {
        User user = userServiceImp.getAuthorizedUser();
        // String roleName = userServiceImp.getRoleName();
        String roleName = user.getRoles().stream().toList().get(0).getRole();
        model.addAttribute("u", user);
        switch (roleName) {
            case "ADMIN" -> {
                return "account_admin";
            }
            case "MANAGER" -> {
                return "account_manager";
            }
            case "USER" -> {
                model.addAttribute("orderNumber", orderServiceImp.getAllOrdersByUserId(user.getId()).size());
                return "account_user";
            }
            default -> {
                return "home";
            }
        }
    }

    @GetMapping("/account/password")
    public String passwordUpdatePage(Model model) {
        return "password_page";
    }

    @PostMapping("/account/password")
    public String updatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Model model) {
        if (bCryptPasswordEncoder.matches(oldPassword, userServiceImp.getAuthorizedUser().getPassword())) {
            userServiceImp.updatePassword(bCryptPasswordEncoder.encode(newPassword));
            return "redirect:/account";
        } else {
            return "redirect:/account/password";
        }
    }


}
