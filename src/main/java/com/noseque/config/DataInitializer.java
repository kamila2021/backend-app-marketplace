package com.noseque.config;

import com.noseque.entities.Rol;
import com.noseque.entities.Usuario;
import com.noseque.enums.EnumRole;
import com.noseque.repositories.RoleRepository;
import com.noseque.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public DataInitializer(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {
        initializeRoles();
        initializeUsers();
    }

    private void initializeRoles() {
        if (!roleRepository.existsByName(EnumRole.USUARIO.name())) {
            Rol userRole = Rol.builder().name(EnumRole.USUARIO.getName()).build();
            roleRepository.save(userRole);
        }
        if (!roleRepository.existsByName(EnumRole.PRESTADOR.name())) {
            Rol providerRole = Rol.builder().name(EnumRole.PRESTADOR.getName()).build();
            roleRepository.save(providerRole);
        }
        if (!roleRepository.existsByName(EnumRole.ADMINISTRADOR.name())) {
            Rol adminRole = Rol.builder().name(EnumRole.ADMINISTRADOR.getName()).build();
            roleRepository.save(adminRole);
        }
    }

    private void initializeUsers() {
        if(usuarioRepository.findByUsername("admin") == null) {
            Usuario newUsuario = new Usuario();
            newUsuario.setFirstname("Admin");
            newUsuario.setLastname("Istrador");
            newUsuario.setUsername("admin");
            newUsuario.setPassword(passwordEncoder.encode("admin123"));
            newUsuario.setTelephone("123456789");
            newUsuario.setAccountLocked(false);
            newUsuario.setEnabled(true);
            newUsuario.setCreatedAt(LocalDateTime.now());
            Rol rol = roleRepository.findRoleByName(EnumRole.ADMINISTRADOR.getName());
            newUsuario.setRoles(new HashSet<>(List.of(rol)));
            usuarioRepository.save(newUsuario);
        }
    }


}

