package com.inclulink.features.catalogo.repository;

import com.inclulink.core.entidades.NivelDiscapacidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelDiscapacidadRepository extends JpaRepository<NivelDiscapacidad, Long> {
}