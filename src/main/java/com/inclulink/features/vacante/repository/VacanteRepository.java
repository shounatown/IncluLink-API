package com.inclulink.features.vacante.repository;

import com.inclulink.core.entidades.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacanteRepository extends JpaRepository<Vacante, Long> {

    List<Vacante> findByEmpresaId(Long idEmpresa);
    List<Vacante> findByCiudadId(Long idCiudad);
    List<Vacante> findByNombreContainingIgnoreCase(String palabraClave);
}