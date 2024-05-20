package com.noseque.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Proveedor")
@EntityListeners(AuditingEntityListener.class)
public class Proveedor extends Usuario{

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<Servicio> servicios;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<HorarioDisponible> fechasDisponibles;

    @OneToMany(mappedBy = "Proveedor",cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "proveedor",cascade = CascadeType.ALL)
    private List<Venta> ventas;

    public Proveedor(){
        super();
    }

}
