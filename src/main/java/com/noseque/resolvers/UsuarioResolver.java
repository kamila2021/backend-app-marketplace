package com.noseque.resolvers;


import com.noseque.dto.ServicioDTO;
import com.noseque.dto.UsuarioDTO;
import com.noseque.entities.Usuario;
import com.noseque.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsuarioResolver {

    private final UsuarioService usuarioService;

    @QueryMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @QueryMapping
    public Usuario obtenerUsuario(@Argument Long id) {
        return usuarioService.obtenerUsuario(id);
    }

    @MutationMapping
    public ResponseEntity eliminarUsuario(@Argument Long usuarioId) {
        return this.usuarioService.eliminarUsuario(usuarioId);
    }
    @MutationMapping
    public Usuario crearUsuario(@Argument UsuarioDTO usuarioDTO) {
        return this.usuarioService.crearUsuario(usuarioDTO);
    }



    @MutationMapping
    public Boolean forgotPassword(@Argument String email) throws Exception {
        return usuarioService.forgotPassword(email);
    }
    @MutationMapping
    public UsuarioDTO editarUsuario(@Argument UsuarioDTO usuarioDTO) {
        System.out.println("entroooo");
        return this.usuarioService.actualizarUsuario(usuarioDTO);
    }
    @MutationMapping
    public Boolean updatePasswordByCode(@Argument String code, @Argument String password) throws Exception {
        return usuarioService.updatePasswordByCode(code, password);
    }

}
