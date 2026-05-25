package com.inclulink.features.catalogo.service;

import com.inclulink.features.catalogo.dto.CatalogoDTO;
import com.inclulink.features.catalogo.dto.CatalogoDependienteDTO;

import java.util.List;

public interface CatalogoService {
    List<CatalogoDTO> findAllPaises();
    List<CatalogoDTO> findAllModalidades();
    List<CatalogoDTO> findAllEstadosVacante();
    List<CatalogoDTO> findAllSectores();
    List<CatalogoDTO> findAllTiposDiscapacidad();
    List<CatalogoDTO> findAllTiposHabilidad();
    List<CatalogoDTO> findAllDiscapacidades(); // Si lo manejas plano
    List<CatalogoDTO> findAllHabilidades();     // Si lo manejas plano

    // Catálogos Dependientes (Filtros)
    List<CatalogoDependienteDTO> findCiudadByPais(Long paisId);
    List<CatalogoDependienteDTO> findHabilidadByTipo(Long tipoId);
    List<CatalogoDependienteDTO> findDiscapacidadByTipo(Long tipoId);
}