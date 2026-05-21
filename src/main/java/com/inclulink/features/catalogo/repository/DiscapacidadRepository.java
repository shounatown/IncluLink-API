package com.inclulink.features.catalogo.repository;

import com.inclulink.core.entidades.Discapacidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscapacidadRepository extends JpaRepository<Discapacidad, Long> {
    List<Discapacidad> findByTipoDiscapacidadId(Long tipoId);
}