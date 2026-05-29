package com.inclulink.features.empresa.repository;

import com.inclulink.core.entidades.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByCorreo(String correo);
    boolean existsByRfc(String rfc);
    boolean existsByCorreo(String correo);
    Optional<Empresa> findByTokenVerificacion(String token);
}