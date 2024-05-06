package com.noseque.services;

import com.noseque.dto.UsuarioDTO;
import com.noseque.entities.Role;
import com.noseque.entities.Usuario;
import com.noseque.repositories.RoleRepository;
import com.noseque.repositories.TokenRepository;
import com.noseque.repositories.UsuarioRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final AuthenticationService authenticationService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;


    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuario(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Usuario con id %s no encontrado", id)));
    }

    public Usuario crearUsuario(UsuarioDTO usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("El usuario es nulo");
        }
        Optional<Usuario> existingUser = Optional.ofNullable(usuarioRepository.findByUsername(usuario.getUsername()));
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        } else {
            Usuario newUsuario = new Usuario();
            newUsuario.setFirstname(usuario.getFirstname());
            newUsuario.setLastname(usuario.getLastname());
            newUsuario.setUsername(usuario.getUsername());
            newUsuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            newUsuario.setTelephone(usuario.getTelephone());
            newUsuario.setAccountLocked(usuario.isAccountLocked());
            newUsuario.setEnabled(usuario.isEnabled());
            newUsuario.setCreatedAt(LocalDateTime.now());
            Usuario usuarioGuardado = usuarioRepository.save(newUsuario);
            asignarRolesAUsuario(usuarioGuardado.getId(), usuario.getRoles());
            return usuarioGuardado;
        }
    }

    public void asignarRolesAUsuario(Integer usuarioId, List<Integer> rolesIds) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Role> roles = roleRepository.findAllById(rolesIds);
        usuario.setRoles(new HashSet<>(roles));
        usuarioRepository.save(usuario);
    }

    public boolean forgotPassword(String username) throws MessagingException, Exception {

        try {
            if (username == null) {
                throw new IllegalArgumentException("El email es nulo");
            }

            Usuario usuario = usuarioRepository.findByUsername(username);
            if (usuario == null) {
                throw new IllegalArgumentException("El usuario no existe");
            }

            this.authenticationService.sendValidationEmail(usuario);

            return true;
        } catch (MessagingException me) {
            throw new MessagingException("Error al enviar correo");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean updatePasswordByCode(String code, String newPassword) throws Exception {

        try {
            if (code == null) {
                throw new IllegalArgumentException("El codigo es nulo");
            }

            Usuario user = this.usuarioRepository.findByResetPasswordToken(code);
            if(user == null) {
                throw new IllegalArgumentException("El usuario no existe");
            }
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setResetPasswordToken(null);
            usuarioRepository.save(user);
            return true;

        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public Usuario getByResetPasswordToken(String token) {
        return usuarioRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(Usuario customer, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(encodedPassword);
        customer.setResetPasswordToken(null);
        usuarioRepository.save(customer);
    }


}
