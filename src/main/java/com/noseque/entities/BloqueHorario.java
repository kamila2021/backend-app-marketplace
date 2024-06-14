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
@Table(name = "BLOQUE_HORARIO")
public class BloqueHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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



