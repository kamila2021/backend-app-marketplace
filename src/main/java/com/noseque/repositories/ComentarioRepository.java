package com.noseque.repositories;

import com.noseque.entities.Comentario;
import com.noseque.entities.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

        Comentario save(Comentario comentario);
}
