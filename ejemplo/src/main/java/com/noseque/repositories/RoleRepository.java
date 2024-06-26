package com.noseque.repositories;

import com.noseque.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findRoleByName(String name);

    boolean existsByName(String name);
}

