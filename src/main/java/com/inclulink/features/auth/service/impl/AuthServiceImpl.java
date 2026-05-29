package com.inclulink.features.auth.service.impl;

import com.inclulink.core.entidades.*;
import com.inclulink.features.auth.dto.*;
import com.inclulink.features.auth.service.AuthService;
import com.inclulink.features.candidato.repository.CandidatoRepository;
import com.inclulink.features.empresa.repository.EmpresaRepository;
import com.inclulink.shared.mail.MailService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    private final CandidatoRepository candidatoRepository;
    private final EmpresaRepository empresaRepository;
    private final MailService mailService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl(CandidatoRepository candidatoRepository,
                           EmpresaRepository empresaRepository,
                           MailService mailService,
                           BCryptPasswordEncoder passwordEncoder) {
        this.candidatoRepository = candidatoRepository;
        this.empresaRepository = empresaRepository;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        Optional<Candidato> candidatoOpt = candidatoRepository.findByCorreo(request.getCorreo());
        if (candidatoOpt.isPresent()) {
            Candidato c = candidatoOpt.get();
            if (passwordEncoder.matches(request.getContrasena(), c.getContrasena())) {
                if (!c.getEstaActivo()) {
                    throw new RuntimeException("Debes confirmar tu correo electrónico antes de iniciar sesión.");
                }
                String token = generarToken(c.getCorreo(), "ROLE_CANDIDATO");
                return new AuthResponseDTO(token, c.getCorreo(), "ROLE_CANDIDATO", c.getId());
            }
        }

        Optional<Empresa> empresaOpt = empresaRepository.findByCorreo(request.getCorreo());
        if (empresaOpt.isPresent()) {
            Empresa e = empresaOpt.get();
            if (passwordEncoder.matches(request.getContrasena(), e.getContrasena())) {
                if (!e.getEstaActiva()) {
                    throw new RuntimeException("Debes confirmar tu correo electrónico antes de iniciar sesión.");
                }
                String token = generarToken(e.getCorreo(), "ROLE_EMPRESA");
                return new AuthResponseDTO(token, e.getCorreo(), "ROLE_EMPRESA", e.getId());
            }
        }

        throw new RuntimeException("Credenciales incorrectas o el usuario no existe");
    }

    @Override
    public String registrarCandidato(CandidatoRegisterRequestDTO dto) {
        if (candidatoRepository.existsByCorreo(dto.getCorreo()) || empresaRepository.existsByCorreo(dto.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado en IncluLink");
        }
        if (candidatoRepository.existsByRfc(dto.getRfc())) {
            throw new RuntimeException("El RFC ya está registrado");
        }

        Candidato nuevo = new Candidato();
        nuevo.setNombre(dto.getNombre());
        nuevo.setCorreo(dto.getCorreo());
        nuevo.setTelefono(dto.getTelefono());
        nuevo.setRfc(dto.getRfc());
        nuevo.setContrasena(passwordEncoder.encode(dto.getContrasena()));

        String tokenVerificacion = UUID.randomUUID().toString();
        nuevo.setEstaActivo(false);
        nuevo.setTokenVerificacion(tokenVerificacion);

        candidatoRepository.save(nuevo);

        try {
            Context context = new Context();
            context.setVariable("nombreUsuario", dto.getNombre());
            context.setVariable("enlaceConfirmacion", "http://localhost:8081/api/v1/auth/confirmar?token=" + tokenVerificacion);

            mailService.enviarCorreoTemplate(
                    dto.getCorreo(),
                    "¡Bienvenido a IncluLink! Confirma tu correo",
                    "mail/candidato/verificacionUsuario",
                    context
            );
            System.out.println("Correo de verificación enviado al candidato: " + dto.getCorreo());
        } catch (Exception ex) {
            System.out.println("Error al enviar correo de candidato: " + ex.getMessage());
        }

        return "Candidato registrado exitosamente. Por favor verifica tu correo electrónico para activar tu cuenta.";
    }

    @Override
    public String registrarEmpresa(EmpresaRegisterRequestDTO dto) {
        if (empresaRepository.existsByCorreo(dto.getCorreo()) || candidatoRepository.existsByCorreo(dto.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado en IncluLink");
        }
        if (empresaRepository.existsByRfc(dto.getRfc())) {
            throw new RuntimeException("El RFC ya está registrado");
        }

        Empresa nueva = new Empresa();
        nueva.setNombre(dto.getNombre());
        nueva.setRfc(dto.getRfc());
        nueva.setCorreo(dto.getCorreo());
        nueva.setDireccion(dto.getDireccion());
        nueva.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        nueva.setFechaRegistro(LocalDate.now());
        String tokenVerificacion = UUID.randomUUID().toString();
        nueva.setEstaActiva(false);
        nueva.setTokenVerificacion(tokenVerificacion);

        nueva.setEsVerificada(false);

        empresaRepository.save(nueva);

        try {
            Context context = new Context();
            context.setVariable("nombreUsuario", dto.getNombre());
            context.setVariable("enlaceConfirmacion", "http://localhost:8081/api/v1/auth/confirmar?token=" + tokenVerificacion);

            mailService.enviarCorreoTemplate(
                    dto.getCorreo(),
                    "IncluLink - Verificación de Cuenta Corporativa",
                    "mail/empresa/verificacionEmpresa",
                    context
            );
            System.out.println("Correo de verificación enviado a la empresa: " + dto.getCorreo());
        } catch (Exception ex) {
            System.out.println("Error al enviar correo de empresa: " + ex.getMessage());
        }

        return "Empresa registrada exitosamente. Por favor verifiquen su correo corporativo para activar la cuenta.";
    }

    private String generarToken(String correo, String rol) {
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(correo)
                .claim("rol", rol)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith(key)
                .compact();
    }



    @Override
    @org.springframework.transaction.annotation.Transactional
    public String confirmarCuenta(String token) {
        Optional<Candidato> candidatoOpt = candidatoRepository.findByTokenVerificacion(token);
        if (candidatoOpt.isPresent()) {
            Candidato candidato = candidatoOpt.get();
            candidato.setEstaActivo(true);
            candidato.setTokenVerificacion(null);
            candidatoRepository.save(candidato);

            try {
                Context context = new Context();
                context.setVariable("nombre", candidato.getNombre());

                mailService.enviarCorreoTemplate(
                        candidato.getCorreo(),
                        "¡Bienvenido a IncluLink!",
                        "mail/candidato/bienvenidaUsuario",
                        context
                );
                System.out.println("Correo de bienvenida enviado tras confirmación al candidato: " + candidato.getCorreo());
            } catch (Exception ex) {
                System.out.println("Error al enviar bienvenida al candidato: " + ex.getMessage());
            }

            return "¡Cuenta de Candidato activada con éxito! Ya puedes iniciar sesión.";
        }

        Optional<Empresa> empresaOpt = empresaRepository.findByTokenVerificacion(token);
        if (empresaOpt.isPresent()) {
            Empresa empresa = empresaOpt.get();
            empresa.setEstaActiva(true);
            empresa.setTokenVerificacion(null);
            empresaRepository.save(empresa);

            try {
                Context context = new Context();
                context.setVariable("nombre", empresa.getNombre());

                mailService.enviarCorreoTemplate(
                        empresa.getCorreo(),
                        "Bienvenido a la red IncluLink",
                        "mail/empresa/bienvenidaEmpresa",
                        context
                );
                System.out.println("Correo de bienvenida enviado tras confirmación a la empresa: " + empresa.getCorreo());
            } catch (Exception ex) {
                System.out.println("Error al enviar bienvenida a la empresa: " + ex.getMessage());
            }

            return "¡Cuenta de Empresa activada con éxito! Ya pueden iniciar sesión.";
        }

        throw new RuntimeException("El enlace de verificación no es válido o ya ha sido utilizado.");
    }
}