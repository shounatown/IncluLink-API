package com.inclulink.features.empresa.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;

@Data
public class EmpresaDTO {
    private Long id;
    private String nombre;
    private String rfc;
    private String correo;
    private String contrasena;
    private String telefono;
    private String direccion;
    private String paginaWeb;
    private String descripcion;
    private LocalDate fechaRegistro;
    private Boolean estaActiva;
    private Boolean esVerificada;

    private Long idSector;
    private Long idCiudad;

    private MultipartFile logoArchivo;
}