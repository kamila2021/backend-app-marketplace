package com.noseque.repositories;

import com.noseque.entities.Servicio;
import com.noseque.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findById(long id);


    Usuario findByUsername(String username);
  Usuario save(Usuario usuario);
  @Query("SELECT u FROM Usuario u WHERE u.resetPasswordToken= ?1")
   Usuario findByResetPasswordToken(String token);


}
