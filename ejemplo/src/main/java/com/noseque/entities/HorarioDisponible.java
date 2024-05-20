package com.noseque.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HorarioDisponible")
@EntityListeners(AuditingEntityListener.class)

public class HorarioDisponible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horarioDisponible")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_fecha")
    private Fecha fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bloque")
    private Bloque bloque;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Proveedor proveedor;

    @OneToOne(mappedBy = "horarioDisponible")
    private Reserva reserva;
}
