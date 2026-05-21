package com.inclulink.features.catalogo.repository;

import com.inclulink.core.entidades.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
    List<Ciudad> findByPaisId(Long paisId);
}