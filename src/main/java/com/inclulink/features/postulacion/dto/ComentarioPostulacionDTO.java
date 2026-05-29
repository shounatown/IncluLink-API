package com.inclulink.features.postulacion.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ComentarioPostulacionDTO {
    private Long id;
    private String comentario;
    private LocalDate fechaComentario;
}