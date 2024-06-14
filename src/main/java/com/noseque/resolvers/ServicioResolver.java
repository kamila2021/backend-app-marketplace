package com.noseque.resolvers;

import com.noseque.dto.EvaluacionDTO;
import com.noseque.dto.ServicioDTO;
import com.noseque.entities.Servicio;
import com.noseque.entities.Usuario;
import com.noseque.services.ServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ServicioResolver {

    private final ServicioService servicioService;

    @MutationMapping
    public ServicioDTO crearServicio(@Argument ServicioDTO servicioDTO) {
        return this.servicioService.crearServicio(servicioDTO);
    }

    @MutationMapping
    public ServicioDTO actualizarServicio(@Argument ServicioDTO servicioDTO) {
        System.out.println("entroooo");
        return this.servicioService.actualizarServicio(servicioDTO);
    }

    @QueryMapping
    public Servicio obtenerServicio(@Argument Long servicioId) {
        return servicioService.obtenerServicio(servicioId);
    }


    @MutationMapping
    public ResponseEntity eliminarServicio(@Argument Long servicioId) {
        return this.servicioService.eliminarServicio(servicioId);
    }

    @QueryMapping
    public ResponseEntity evaluarServicio(@Argument Long servicioId, @Argument Long usuarioId, @Argument EvaluacionDTO evaluacionDTO) {
        return this.servicioService.evaluarServicio(servicioId, usuarioId, evaluacionDTO);
    }

    @QueryMapping
    public List<Servicio> listarServicios() {
        return this.servicioService.listarServicios();
    }

    @QueryMapping
    public ResponseEntity listarServiciosPorUsuario(@Argument Long usuarioId) {
        System.out.println("entro a la query");

        return this.servicioService.listarServiciosPorUsuario(usuarioId);
    }

    @QueryMapping
    public ResponseEntity ventasTotalesPorUsuario(@Argument Long usuarioId) {
        return this.servicioService.ventasTotalesPorUsuario(usuarioId);
    }

    @QueryMapping
    public ResponseEntity ventasPorFecha(@Argument Long usuarioId, @Argument String fechaInicio, @Argument String fechaFin) {
        return this.servicioService.ventasPorFecha(usuarioId, fechaInicio, fechaFin);
    }

    @QueryMapping
    public ResponseEntity topCincoServicios(@Argument Long usuarioId) {
        return null;
    }
}
