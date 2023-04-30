package com.bookstoreversion2.services;

import com.bookstoreversion2.data.entities.Basket;
import com.bookstoreversion2.data.entities.User;
import com.bookstoreversion2.data.repo.BasketRepository;
import com.bookstoreversion2.data.repo.UserRepository;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.passay.DigestDictionaryRule.ERROR_CODE;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BasketRepository basketRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getUserByEmail(String email) {
        User userFindByEmail = new User();
        for (User user : userRepository.findAll()) {
            if (user.getUsername().equals(email))
                userFindByEmail = user;
        }
        return userFindByEmail;
    }

    @Override
    public void createNewAccount(User user) {
        userRepository.save(user);
        if (user.getRoles().stream().toList().get(0).getRole().equals("USER")) {
            Basket basket = new Basket();
            basket.setUser(user);
            basketRepository.save(basket);
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getAuthorizedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = getUserByEmail(username);
        return user;
    }

    @Override
    public String generatePassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);
        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
        return password;
    }

    @Override
    public void updatePassword(String newPassword) {
        User user = getAuthorizedUser();
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public Basket getAuthorizedUserBasket() {
        User user = getAuthorizedUser();
        Basket basket = user.getBasket();
        return basket;
    }

    @Override
    public List<User> getManagerList() {
        List<User> managers = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (user.getRoles().stream().toList().get(0).getRole().equals("MANAGER")) {
                managers.add(user);
            }
        }
        return managers;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public String getRoleName() {
        return getAuthorizedUser().getRoles().stream().toList().get(0).getRole();
    }

}
