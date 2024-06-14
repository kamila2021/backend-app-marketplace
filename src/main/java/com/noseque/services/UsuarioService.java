package com.noseque.services;

import com.noseque.dto.ServicioDTO;
import com.noseque.dto.UsuarioDTO;
import com.noseque.entities.*;
import com.noseque.repositories.RoleRepository;
import com.noseque.repositories.TokenRepository;
import com.noseque.repositories.UsuarioRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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

    public Usuario obtenerUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Usuario con id %s no encontrado", id)));
    }
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) {
        try {
            System.out.println("1 Buscando usuario por ID: " + usuarioDTO.getId());
            Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(usuarioDTO.getId());
            if (optionalUsuario.isPresent()) {
                System.out.println("2 Usuario encontrado");
                Usuario usuario = optionalUsuario.get();
                usuario.setFirstname(usuarioDTO.getFirstname());
                usuario.setLastname(usuarioDTO.getLastname());
                usuario.setUsername(usuarioDTO.getUsername());
                usuario.setPassword(usuarioDTO.getPassword());
                usuario.setTelephone(usuarioDTO.getTelephone());
                // Crear un conjunto para almacenar los roles actualizados del usuario
                Set<Rol> rolesActualizados = new HashSet<>();

                // Iterar sobre los IDs de roles del DTO
                for (Long roleId : usuarioDTO.getRoles()) {
                    // Buscar el rol correspondiente en la base de datos
                    Optional<Rol> optionalRol = roleRepository.findById(roleId);
                    if (optionalRol.isPresent()) {
                        // Si se encuentra el rol, agregarlo al conjunto de roles actualizados del usuario
                        rolesActualizados.add(optionalRol.get());
                    } else {
                        // Manejar el caso en el que no se encuentre el rol
                        // Por ejemplo, lanzar una excepción o registrar un mensaje de error
                    }
                }

                // Actualizar los roles del usuario con el conjunto de roles actualizados
                usuario.setRoles(rolesActualizados);

                usuario.setLastModifiedDate(LocalDateTime.now()); // Opcional: Actualizar la fecha de modificación aquí
                Usuario savedUsuario = this.usuarioRepository.save(usuario);
                return usuarioDTO;
            } else {
                System.out.println("Usuario no encontrado");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
            // Manejar la excepción de manera más específica
            return null;
        }
    }

    public ResponseEntity eliminarUsuario(Long usuarioId) {
        try {
            this.usuarioRepository.deleteById(usuarioId);
            return ResponseEntity.ok("Usuario eliminado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting Usuario: " + e.getMessage());
        }
    }
    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        try {
                Usuario newUsuario = new Usuario();
                newUsuario.setFirstname(usuarioDTO.getFirstname());
                newUsuario.setLastname(usuarioDTO.getLastname());
                newUsuario.setUsername(usuarioDTO.getUsername());
                newUsuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
                newUsuario.setTelephone(usuarioDTO.getTelephone());
                newUsuario.setAccountLocked(usuarioDTO.isAccountLocked());
                newUsuario.setEnabled(usuarioDTO.isEnabled());
                newUsuario.setCreatedAt(LocalDateTime.now());


                // Guardar el nuevo usuario en la base de datos
                Usuario usuarioGuardado = usuarioRepository.save(newUsuario);
            // Asignar roles al usuario
            asignarRolesAUsuario(usuarioGuardado.getId(), usuarioDTO.getRoles());


                // Devolver el DTO del usuario creado
                return usuarioGuardado;

        } catch (Exception e) {
            e.printStackTrace(); // Imprime la traza de la excepción en la consola
            return null;
        }
    }

    public void asignarRolesAUsuario(Long usuarioId, List<Long> rolesIds) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Rol> roles = roleRepository.findAllById(rolesIds);
        usuario.setRoles(new HashSet<>(roles));
        usuarioRepository.save(usuario);
    }

    public boolean forgotPassword(String username) throws Exception {

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
