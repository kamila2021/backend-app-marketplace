package com.noseque.config;

import com.noseque.enums.EnumRole;
import com.noseque.entities.Role;
import com.noseque.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        initializeRoles();
    }

    private void initializeRoles() {
        if (!roleRepository.existsByName(EnumRole.USUARIO.name())) {
            Role userRole = Role.builder().name(EnumRole.USUARIO.getName()).build();
            roleRepository.save(userRole);
        }
        if (!roleRepository.existsByName(EnumRole.PRESTADOR.name())) {
            Role providerRole = Role.builder().name(EnumRole.PRESTADOR.getName()).build();
            roleRepository.save(providerRole);
        }
        if (!roleRepository.existsByName(EnumRole.ADMINISTRADOR.name())) {
            Role adminRole = Role.builder().name(EnumRole.ADMINISTRADOR.getName()).build();
            roleRepository.save(adminRole);
        }
    }




}

