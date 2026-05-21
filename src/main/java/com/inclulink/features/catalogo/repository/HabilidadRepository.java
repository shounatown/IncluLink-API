package com.inclulink.features.catalogo.repository;

import com.inclulink.core.entidades.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabilidadRepository extends JpaRepository<Habilidad, Long> {
    List<Habilidad> findByTipoHabilidadId(Long tipoId);
}
