package com.bookstoreversion2.services;

import com.bookstoreversion2.entities.Basket;
import com.bookstoreversion2.entities.Role;
import com.bookstoreversion2.entities.User;
import com.bookstoreversion2.repo.BasketRepository;
import com.bookstoreversion2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        for(User user : userRepository.findAll()){
            if(user.getUsername().equals(email))
                userFindByEmail = user;
        }
        return userFindByEmail;
    }

    @Override
    public void createNewAccount(User user) {
        userRepository.save(user);
        if(user.getRoles().stream().toList().get(0).getRole().equals("USER")){
            Basket basket = new Basket();
          //  user.setBasket(basket);
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
        String username ="";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = getUserByEmail(username);
        return user;
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
//        String roleName = "";
//        for (Role role : getAuthorizedUser().getRoles().stream().toList()) {
//            if (role.getRole().equals("USER")) {
//                roleName = "user";
//            }else if(role.getRole().equals("MANAGER")) {
//                roleName = "manager";
//            } else if(role.getRole().equals("ADMIN")) {
//                roleName = "admin";
//            }
//        }
//        return roleName;
    }
}
