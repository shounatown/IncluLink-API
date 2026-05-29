package com.inclulink.features.catalogo.repository;

import com.inclulink.core.entidades.NivelHabilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelHabilidadRepository extends JpaRepository<NivelHabilidad, Long> {
}