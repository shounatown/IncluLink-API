package com.inclulink.features.candidato.repository;

import com.inclulink.core.entidades.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    Optional<Candidato> findByCorreo(String correo);
    boolean existsByRfc(String rfc);
    boolean existsByCorreo(String correo);
    Optional<Candidato> findByTokenVerificacion(String token);
}