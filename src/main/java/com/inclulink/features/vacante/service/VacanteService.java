package com.inclulink.features.vacante.service;

import com.inclulink.core.entidades.Vacante;

import java.util.List;

public interface VacanteService {
    List<Vacante> findAll();
    Vacante findById(Long id);
    Vacante save(Vacante vacante);
    void deleteById(Long id);

    List<Vacante> findByEmpresa(Long idEmpresa);
    List<Vacante> findByCiudad(Long idCiudad);
    List<Vacante> findByName(String palabraClave);
}