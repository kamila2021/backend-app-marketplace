package com.noseque.services;

import com.noseque.entities.Usuario;
import com.noseque.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private final UsuarioRepository repository;
    private PasswordEncoder encoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario;
        try {
            usuario = repository.findByUsername(username);

        } catch(Exception e) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        Set<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())) // Utilizar SimpleGrantedAuthority
                .collect(Collectors.toSet());

        return User.withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(authorities)
                .build();
    }
    public String addUser(Usuario usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        repository.save(usuario);
        return "User Added Successfully";
    }

}