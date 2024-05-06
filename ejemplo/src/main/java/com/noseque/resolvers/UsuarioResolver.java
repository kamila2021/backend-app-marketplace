package com.noseque.resolvers;


import com.noseque.dto.UsuarioDTO;
import com.noseque.entities.Usuario;
import com.noseque.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
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
    public Usuario getUsuario(@Argument Integer id) {
        return usuarioService.getUsuario(id);
    }

    @MutationMapping
    public Usuario crearUsuario(@Argument UsuarioDTO usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @MutationMapping
    public Boolean forgotPassword(@Argument String email) throws Exception {
        return usuarioService.forgotPassword(email);
    }

    @MutationMapping
    public Boolean updatePasswordByCode(@Argument String code, @Argument String password) throws Exception {
        return usuarioService.updatePasswordByCode(code, password);
    }

}
