package com.inclulink.features.auth.dto;

import lombok.Data;

@Data
public class EmpresaRegisterRequestDTO {
    private String nombre;
    private String rfc;
    private String correo;
    private String contrasena;
    private String direccion;
}
