package com.bookstoreversion2.services;


import com.bookstoreversion2.entities.User;

public interface UserService {

    User getUserById(Long id);
    User getUserByEmail(String email);
    void createNewAccount(User user);
    void deleteUserById(Long id);
    User getAuthorizedUser();

    String generatePassword();

}
