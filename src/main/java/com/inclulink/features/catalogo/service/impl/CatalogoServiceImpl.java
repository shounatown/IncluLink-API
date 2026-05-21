package com.inclulink.features.catalogo.service.impl;

import com.inclulink.features.catalogo.dto.CatalogoDTO;
import com.inclulink.features.catalogo.dto.CatalogoDependienteDTO;
import com.inclulink.features.catalogo.repository.*;
import com.inclulink.features.catalogo.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CatalogoServiceImpl implements CatalogoService {

    private final PaisRepository paisRepository;
    private final CiudadRepository ciudadRepository;
    private final ModalidadRepository modalidadRepository;
    private final EstadoVacanteRepository estadoVacanteRepository;
    private final SectorRepository sectorRepository;
    private final TipoDiscapacidadRepository tipoDiscapacidadRepository;
    private final DiscapacidadRepository discapacidadRepository;
    private final TipoHabilidadRepository tipoHabilidadRepository;
    private final HabilidadRepository habilidadRepository;

    @Override
    public List<CatalogoDTO> findAllPaises() {
        return paisRepository.findAll().stream()
                .map(p -> new CatalogoDTO(p.getId(), p.getNombre())).toList();
    }

    @Override
    public List<CatalogoDTO> findAllModalidades() {
        return modalidadRepository.findAll().stream()
                .map(m -> new CatalogoDTO(m.getId(), m.getNombre())).toList();
    }

    @Override
    public List<CatalogoDTO> findAllEstadosVacante() {
        return estadoVacanteRepository.findAll().stream()
                .map(e -> new CatalogoDTO(e.getId(), e.getNombre())).toList();
    }

    @Override
    public List<CatalogoDTO> findAllSectores() {
        return sectorRepository.findAll().stream()
                .map(s -> new CatalogoDTO(s.getId(), s.getNombre())).toList();
    }

    @Override
    public List<CatalogoDTO> findAllTiposDiscapacidad() {
        return tipoDiscapacidadRepository.findAll().stream()
                .map(t -> new CatalogoDTO(t.getId(), t.getNombre())).toList();
    }

    @Override
    public List<CatalogoDTO> findAllTiposHabilidad() {
        return tipoHabilidadRepository.findAll().stream()
                .map(t -> new CatalogoDTO(t.getId(), t.getNombre())).toList();
    }

    @Override
    public List<CatalogoDTO> findAllDiscapacidades() {
        return discapacidadRepository.findAll().stream()
                .map(d -> new CatalogoDTO(d.getId(), d.getNombre())).toList();
    }

    @Override
    public List<CatalogoDTO> findAllHabilidades() {
        return habilidadRepository.findAll().stream()
                .map(h -> new CatalogoDTO(h.getId(), h.getNombre())).toList();
    }



    //Catalogos dependientes

    @Override
    public List<CatalogoDependienteDTO> findCiudadByPais(Long paisId) {
        return ciudadRepository.findByPaisId(paisId).stream()
                .map(c -> new CatalogoDependienteDTO(c.getId(), c.getNombre(), c.getPais().getId()))
                .toList();
    }

    @Override
    public List<CatalogoDependienteDTO> findHabilidadByTipo(Long tipoId) {
        return habilidadRepository.findByTipoHabilidadId(tipoId).stream()
                .map(h -> new CatalogoDependienteDTO(h.getId(), h.getNombre(), h.getTipoHabilidad().getId()))
                .toList();
    }

    @Override
    public List<CatalogoDependienteDTO> findDiscapacidadByTipo(Long tipoId) {
        return discapacidadRepository.findByTipoDiscapacidadId(tipoId).stream()
                .map(d -> new CatalogoDependienteDTO(d.getId(), d.getNombre(), d.getTipoDiscapacidad().getId()))
                .toList();
    }
}