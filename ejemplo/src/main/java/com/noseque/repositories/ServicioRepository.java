package com.noseque.repositories;

import com.noseque.entities.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

        Servicio save(Servicio servicio);
}
