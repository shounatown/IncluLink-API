package com.inclulink.features.postulacion.dto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PostulacionDTO {
    private Long id;
    private LocalDate fechaPostulacion;
    private LocalDate fechaActualizacion;

    private Long idVacante;
    private Long idCandidato;
    private Long idEstadoPostulacion;
}