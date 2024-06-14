package com.noseque.repositories;

import com.noseque.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Rol,Long> {
    Rol findRoleByName(String name);

    boolean existsByName(String name);
    Optional<Rol> findById(Long id);


}

