package com.noseque.repositories;

import com.noseque.entities.Servicio;
import com.noseque.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

        Servicio save(Servicio servicio);

        List<Servicio> findAllByAutor(Usuario autor);


        @Query("SELECT s FROM Servicio s WHERE s.autor = ?1 AND s.fechaInicio < ?2 AND s.fechaFin > ?3")
        List<Servicio> findAllByAutorAndFechaInicioBeforeAndFechaFinAfter(Usuario autor, LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
