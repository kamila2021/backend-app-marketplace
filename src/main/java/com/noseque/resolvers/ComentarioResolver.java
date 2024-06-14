package com.noseque.resolvers;

import com.noseque.dto.ComentarioDTO;
import com.noseque.entities.Servicio;
import com.noseque.services.ComentarioService;
import com.noseque.services.ServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ComentarioResolver {

    private final ComentarioService comentarioService;

    @QueryMapping
    public ResponseEntity crearComentario(@Argument ComentarioDTO comentarioDTO) {
        return null;
    }

    @QueryMapping
    public ResponseEntity actualizarComentario(@Argument ComentarioDTO comentarioDTO) {
        return null;
    }
}
