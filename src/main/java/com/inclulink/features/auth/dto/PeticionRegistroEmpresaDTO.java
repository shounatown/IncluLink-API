package com.inclulink.features.auth.dto;

import lombok.Data;

@Data
public class PeticionRegistroEmpresaDTO {
    private String nombre;
    private String telefono;
    private String correo;
    private String contrasena;
    private String sector;
}
