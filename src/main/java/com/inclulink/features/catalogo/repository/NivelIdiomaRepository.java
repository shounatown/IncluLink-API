package com.inclulink.features.catalogo.repository;

import com.inclulink.core.entidades.NivelIdioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelIdiomaRepository extends JpaRepository<NivelIdioma, Long> {
}