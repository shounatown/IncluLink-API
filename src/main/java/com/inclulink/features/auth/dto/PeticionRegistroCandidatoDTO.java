package com.inclulink.features.auth.dto;

import lombok.Data;

@Data
public class PeticionRegistroCandidatoDTO {
    private String nombre;
    private String telefono;
    private String genero;
    private String correo;
    private String contrasena;
}
