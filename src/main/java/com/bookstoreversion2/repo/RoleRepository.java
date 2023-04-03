package com.bookstoreversion2.repo;

import com.bookstoreversion2.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
