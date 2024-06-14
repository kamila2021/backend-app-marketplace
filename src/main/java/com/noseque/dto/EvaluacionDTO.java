package com.noseque.dto;

import com.noseque.entities.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class EvaluacionDTO {
    private Long id;
    private Integer puntuacion;
    private Long usuario;
}