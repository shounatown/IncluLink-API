package com.inclulink.features.auth.service;

import com.inclulink.features.auth.dto.AuthResponseDTO;
import com.inclulink.features.auth.dto.CandidatoRegisterRequestDTO;
import com.inclulink.features.auth.dto.EmpresaRegisterRequestDTO;
import com.inclulink.features.auth.dto.LoginRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponseDTO login(LoginRequestDTO request);
    String registrarCandidato(CandidatoRegisterRequestDTO dto);
    String registrarEmpresa(EmpresaRegisterRequestDTO dto);
    String confirmarCuenta(String token);
}
