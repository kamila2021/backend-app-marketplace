package com.noseque.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Fecha")
@EntityListeners(AuditingEntityListener.class)
public class Fecha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fecha")
    private Integer id;

    private LocalDate fecha;

    @OneToMany(mappedBy = "fecha")
    private List<HorarioDisponible> fechasDisponibles;
}
