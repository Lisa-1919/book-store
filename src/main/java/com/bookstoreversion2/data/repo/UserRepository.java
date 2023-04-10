package com.bookstoreversion2.data.repo;

import com.bookstoreversion2.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}