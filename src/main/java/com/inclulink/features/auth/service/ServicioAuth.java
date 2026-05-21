package com.inclulink.features.auth.service;

import com.inclulink.features.auth.dto.PeticionLoginDTO;
import com.inclulink.features.auth.dto.PeticionRegistroCandidatoDTO;
import com.inclulink.features.auth.dto.PeticionRegistroEmpresaDTO;
import org.springframework.stereotype.Service;

@Service
public class ServicioAuth {

    public String login(PeticionLoginDTO peticion) {
        return "Bienvenido " + peticion.getCorreo();
    }

    public String registroCandidato(PeticionRegistroCandidatoDTO peticion) {
        return "Bienvenido " + peticion.getCorreo();
    }

    public String registroEmpresa(PeticionRegistroEmpresaDTO peticion) {
        return "Bienvenido " + peticion.getCorreo();
    }
}
