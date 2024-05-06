package com.noseque.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    private String id;

    private String nombre;

    private double precio;

    private int cantidad;

    @ManyToOne
    private Categoria categoria;
}
