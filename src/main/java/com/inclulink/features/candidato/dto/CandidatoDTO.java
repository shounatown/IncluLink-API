package com.inclulink.features.candidato.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;

@Data
public class CandidatoDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String contrasena;
    private String telefono;
    private String direccion;
    private String rfc;
    private LocalDate fechaNacimiento;
    private Integer anosExperiencia;
    private String descripcion;
    private String github;
    private LocalDate fechaRegistro;
    private Boolean estaActivo;

    private Long idCiudad;
    private Long idNivelEstudios;

    private MultipartFile fotoArchivo;
    private MultipartFile curriculumArchivo;
}