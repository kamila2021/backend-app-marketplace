package com.noseque.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Reserva")
@EntityListeners(AuditingEntityListener.class)

public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_horarioDisponible")
    private HorarioDisponible horarioDisponible;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Proveedor proveedor;

    @OneToMany(mappedBy = "reserva",cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToOne(mappedBy = "reserva")
    private Venta venta;
}
