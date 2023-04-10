package com.bookstoreversion2.data.repo;

import com.bookstoreversion2.data.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
