package com.inclulink.features.catalogo.repository;

import com.inclulink.core.entidades.EstadoPostulacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoPostulacionRepository extends JpaRepository<EstadoPostulacion, Long> {

    Optional<EstadoPostulacion> findByNombre(String nombre);
}