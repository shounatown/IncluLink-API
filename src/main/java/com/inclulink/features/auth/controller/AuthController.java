package com.inclulink.features.auth.controller;

import com.inclulink.features.auth.dto.*;
import com.inclulink.features.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register/candidato")
    public ResponseEntity<String> registrarCandidato(@RequestBody CandidatoRegisterRequestDTO request) {
        return ResponseEntity.ok(authService.registrarCandidato(request));
    }

    @PostMapping("/register/empresa")
    public ResponseEntity<String> registrarEmpresa(@RequestBody EmpresaRegisterRequestDTO request) {
        return ResponseEntity.ok(authService.registrarEmpresa(request));
    }

    @GetMapping("/confirmar")
    public ResponseEntity<String> confirmarCuenta(@RequestParam("token") String token) {
        try {
            String mensajeExito = authService.confirmarCuenta(token);
            return ResponseEntity.ok(mensajeExito);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}