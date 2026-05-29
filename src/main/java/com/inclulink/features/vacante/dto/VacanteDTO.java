package com.inclulink.features.vacante.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class VacanteDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String requisitos;
    private BigDecimal salarioMaximo;
    private BigDecimal salarioMinimo;
    private Integer anosExperiencia;
    private LocalDate fechaPublicacion;
    private LocalDate fechaCierre;
    private Integer cupo;
    private Integer vistasContador;

    private Long idEstadoVacante;
    private Long idEmpresa;
    private Long idCiudad;
    private Long idModalidad;
    private Long idJornada;

    private List<Long> idAccesibilidades;

    private MultipartFile fotoArchivo;
}