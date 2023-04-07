package com.bookstoreversion2.controllers;

import com.bookstoreversion2.entities.Role;
import com.bookstoreversion2.entities.User;
import com.bookstoreversion2.services.UserServiceImp;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Random;
import java.util.stream.IntStream;

import static org.passay.DigestDictionaryRule.ERROR_CODE;

@Controller
public class AdminController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public AdminController() {
    }

    @GetMapping("/admin")
    public String adminHomePage(Model model) {
        model.addAttribute("user", userServiceImp.getAuthorizedUser());
        return "account_admin";
    }


    @GetMapping("/admin/managers/add")
    public String addManagerPage(Model model) {
        return "manager_registration";
    }

    @PostMapping("/admin/managers/add")
    public String addManager(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phone,
                             @RequestParam String email, Model model) {
       String password = userServiceImp.generatePassword();
        try {
            Writer writer = new FileWriter("D:/book-store-version-2/managers/" + firstName + "_" + lastName + ".txt", false);
            writer.write(email + " " + password + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = new User(email, phone, firstName, lastName, bCryptPasswordEncoder.encode(password), Collections.singleton(new Role(2, "MANAGER")));
        userServiceImp.createNewAccount(user);

        return "redirect:/admin";
    }

    @PostMapping("/admin/managers/{id}/delete")
    public String deleteManager(@RequestParam User user, Model model) {
        userServiceImp.deleteUserById(user.getId());
        return "redirect:/admin";
    }
}
