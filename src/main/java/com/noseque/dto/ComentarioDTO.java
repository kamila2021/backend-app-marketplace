package com.noseque.dto;

import com.noseque.entities.Usuario;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ComentarioDTO {
    private Long id;
    private Long usuario;
    private String comentario;
}
