package com.noseque.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Bloque")
@EntityListeners(AuditingEntityListener.class)
public class Bloque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bloque")
    private Integer id;

    @OneToMany(mappedBy = "bloque")
    private List<HorarioDisponible> fechasDisponibles;

    private String nombre;
}
