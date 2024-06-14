package com.noseque.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SERVICIO")
@EntityListeners(AuditingEntityListener.class)
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer costo;

    private String direccion;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Fotos> fotos;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Categoria> categorias;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Comentario> comentarios;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Evaluacion> evaluaciones;

    @ManyToOne
    private Usuario autor;

    @Column(name = "FECHA_INICIO")
    private String fechaInicio;

    @Column(name = "FECHA_FIN")
    private String fechaFin;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    private Long createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastUpdatedAt;

    private Long lastUpdatedBy;

}



