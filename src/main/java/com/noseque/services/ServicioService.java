package com.noseque.services;

import com.noseque.dto.EvaluacionDTO;
import com.noseque.dto.ServicioDTO;
import com.noseque.entities.*;
import com.noseque.repositories.CategoriaRepository;
import com.noseque.repositories.EvaluacionRepository;
import com.noseque.repositories.ServicioRepository;
import com.noseque.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioService {

    private final ServicioRepository servicioRepository;
    private final CategoriaRepository categoriaRepository;
    private final EvaluacionRepository evaluacionRepository;
    private final UsuarioRepository userRepository;
    private final UsuarioRepository usuarioRepository;


    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }
    public ServicioDTO crearServicio(ServicioDTO servicioDTO) {
        try {
            Servicio servicio = new Servicio();
            servicio.setNombre(servicioDTO.getNombre());
            servicio.setCosto(servicioDTO.getCosto());
            servicio.setDireccion(servicioDTO.getDireccion());

            if(servicioDTO.getFotos() != null) {
                List<Fotos> fotos = new ArrayList<>();
                Arrays.stream(servicioDTO.getFotos()).forEach(foto -> {
                    Fotos f = new Fotos();
                    f.setNombreFoto(foto);
                    fotos.add(f);
                });
                servicio.setFotos(fotos);
            }

            if(servicioDTO.getCategorias()  != null) {
                List<Categoria> categorias = new ArrayList<>();
                Arrays.stream(servicioDTO.getCategorias()).forEach(categoria -> {
                    Categoria c = this.categoriaRepository.findById(categoria).get();
                    categorias.add(c);
                });
                servicio.setCategorias(categorias);
            }

            servicio.setAutor(this.userRepository.findById(servicioDTO.getAutor()).get());
            servicio.setFechaInicio(servicioDTO.getFechaInicio());
            servicio.setFechaFin(servicioDTO.getFechaFin());
            servicio.setCreatedAt(LocalDateTime.now());
            servicio.setCreatedBy(1L);
            Servicio servicioNuevo = servicioRepository.save(servicio);
            return servicioDTO;
        } catch (Exception e) {
            return null;
        }
    }

    public ServicioDTO actualizarServicio(ServicioDTO servicioDTO) {
        try {
            Servicio servicio = this.servicioRepository.findById(servicioDTO.getId()).get();
            servicio.setNombre(servicioDTO.getNombre());
            servicio.setCosto(servicioDTO.getCosto());
            servicio.setDireccion(servicioDTO.getDireccion());
            if(servicioDTO.getFotos() != null) {
                List<Fotos> fotos = new ArrayList<>();
                Arrays.stream(servicioDTO.getFotos()).forEach(foto -> {
                    Fotos f = new Fotos();
                    f.setNombreFoto(foto);
                    fotos.add(f);
                });
                servicio.setFotos(fotos);
            }

            if(servicioDTO.getCategorias()  != null) {
                List<Categoria> categorias = new ArrayList<>();
                Arrays.stream(servicioDTO.getCategorias()).forEach(categoria -> {
                    Categoria c = this.categoriaRepository.findById(categoria).get();
                    categorias.add(c);
                });
                servicio.setCategorias(categorias);
            }
            servicio.setAutor(this.userRepository.findById(servicioDTO.getAutor()).get());
            servicio.setFechaInicio(servicioDTO.getFechaInicio());
            servicio.setFechaFin(servicioDTO.getFechaFin());
            servicio.setLastUpdatedAt(LocalDateTime.now());
            servicio.setLastUpdatedBy(1L);
            Servicio savedServicio = this.servicioRepository.save(servicio);
            return servicioDTO;
        } catch (Exception e) {
            return null;
        }
    }

    public Servicio obtenerServicio(Long servicioId) {
        return servicioRepository.findById(servicioId)
                .orElseThrow(() -> new RuntimeException(String.format("Usuario con id %s no encontrado", servicioId)));
    }

    public ResponseEntity eliminarServicio(Long servicioId) {
        try {
            this.servicioRepository.deleteById(servicioId);
            return ResponseEntity.ok("Servicio eliminado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting Servicio: " + e.getMessage());
        }
    }

    public ResponseEntity evaluarServicio(Long servicioId, Long usuarioId, EvaluacionDTO evaluacionDTO) {
        try {
            Evaluacion evaluacion = new Evaluacion();
            evaluacion.setPuntuacion(evaluacionDTO.getPuntuacion());
            evaluacion.setUsuario(this.userRepository.findById(usuarioId).get());
            Servicio servicio = this.servicioRepository.findById(servicioId).get();
            servicio.setEvaluaciones(List.of(evaluacion));
            this.servicioRepository.save(servicio);
            Evaluacion savedEvaluacion = this.evaluacionRepository.save(evaluacion);
            return ResponseEntity.ok(savedEvaluacion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting Servicio: " + e.getMessage());
        }
    }


    public ResponseEntity listarServiciosPorUsuario(Long usuarioId) {
        try {
            System.out.println("1");

            List<Servicio> servicios = this.servicioRepository.findAllByAutor(this.userRepository.findById(usuarioId).get());
            System.out.println("entro");
            return ResponseEntity.ok("lista"+ servicios);

        } catch (Exception e) {
            System.out.println("2");

            return ResponseEntity.badRequest().body("Error getting Servicios: " + e.getMessage());
        }
    }

    public ResponseEntity ventasTotalesPorUsuario(Long usuarioId) {
        try {
            List<Servicio> servicios = this.servicioRepository.findAllByAutor(this.userRepository.findById(usuarioId).get());
            int totalCosto = 0;
            for (Servicio servicio : servicios) {
                totalCosto += servicio.getCosto();
            }
            return ResponseEntity.ok(totalCosto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting Servicios: " + e.getMessage());
        }
    }

    public ResponseEntity ventasPorFecha(Long usuarioId, String fechaInicio, String fechaFin) {
        try {
            List<Servicio> servicios = this.servicioRepository.findAllByAutorAndFechaInicioBeforeAndFechaFinAfter(this.userRepository.findById(usuarioId).get(), LocalDateTime.parse(fechaInicio), LocalDateTime.parse(fechaFin));
            return ResponseEntity.ok(servicios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting Servicios: " + e.getMessage());
        }
    }

    public ResponseEntity topCincoServicios(Long usuarioId) {
        try {
            List<Servicio> servicios = this.servicioRepository.findAllByAutor(this.userRepository.findById(usuarioId).get());
            return ResponseEntity.ok(servicios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting Servicios: " + e.getMessage());
        }
    }


}
