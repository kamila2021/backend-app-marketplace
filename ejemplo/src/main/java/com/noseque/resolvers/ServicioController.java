package com.noseque.resolvers;

import com.noseque.entities.Servicio;
import com.noseque.repositories.ServicioRepository;
import com.noseque.services.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private ServicioRepository servicioRepository;
    @QueryMapping
    public Servicio listarServicioPorId(@Argument Integer id)
    {
        return servicioRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Servicio %s no encontrado",id))
        );
    }
    @Secured("ROLE_USER")
    @QueryMapping
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }
/*
    @Secured("ROLE_MODERATOR")
    @MutationMapping
    public Servicio crearServicio(@Argument String nombre, @Argument Integer costo, @Argument String direccion) {
        // Validar que los argumentos no sean nulos o vacíos
        if (nombre == null || nombre.isEmpty() || costo == null || direccion == null || direccion.isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }

        // Crear un nuevo objeto Servicio y establecer los valores
        Servicio servicio = new Servicio();
        servicio.setNombre(nombre);
        servicio.setCosto(costo);
        servicio.setDireccion(direccion);

        // Guardar el servicio en la base de datos
        return servicioRepository.save(servicio);
    }*/
}

