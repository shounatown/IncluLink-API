package com.inclulink.features.auth.controller;

import com.inclulink.features.auth.dto.PeticionLoginDTO;
import com.inclulink.features.auth.dto.PeticionRegistroCandidatoDTO;
import com.inclulink.features.auth.dto.PeticionRegistroEmpresaDTO;
import com.inclulink.features.auth.service.ServicioAuth;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {

    private final ServicioAuth servicioAuth;

    public ControllerAuth(ServicioAuth servicioAuth) {
        this.servicioAuth = servicioAuth;
    }

    @PostMapping("/login")
    public String login(@RequestBody PeticionLoginDTO peticion) {
        return servicioAuth.login(peticion);
    }

    @PostMapping("/registro/candidato")
    public String registroCandidato(@RequestBody PeticionRegistroCandidatoDTO peticion) {
        return servicioAuth.registroCandidato(peticion);
    }

    @PostMapping("/registro/empresa")
    public String registroEmpresa(@RequestBody PeticionRegistroEmpresaDTO peticion) {
        return servicioAuth.registroEmpresa(peticion);
    }
}
