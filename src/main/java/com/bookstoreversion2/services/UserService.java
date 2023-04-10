package com.bookstoreversion2.services;


import com.bookstoreversion2.data.entities.Basket;
import com.bookstoreversion2.data.entities.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);
    User getUserByEmail(String email);
    void createNewAccount(User user);
    void deleteUserById(Long id);
    User getAuthorizedUser();
    String generatePassword();
    void updatePassword(String oldPassword, String newPassword);
    Basket getAuthorizedUserBasket();
    List<User> getManagerList();

}
