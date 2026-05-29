package com.inclulink.features.auth.dto;

import lombok.Data;

@Data
public class CandidatoRegisterRequestDTO {
    private String nombre;
    private String correo;
    private String contrasena;
    private String rfc;
    private String telefono;
}
