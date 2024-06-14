package com.noseque.repositories;

import com.noseque.entities.Evaluacion;
import com.noseque.entities.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

        Evaluacion save(Evaluacion evaluacion);
}
