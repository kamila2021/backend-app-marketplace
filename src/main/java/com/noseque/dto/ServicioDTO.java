package com.noseque.dto;

import com.noseque.entities.*;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ServicioDTO {
    private Long id;
    private String nombre;
    private Integer costo;
    private String direccion;
    private String[] fotos;
    private Long[] categorias;
    private Long[] comentarios;
    private Long[] evaluaciones;
    private Long autor;
    private String fechaInicio;
    private String fechaFin;
}
