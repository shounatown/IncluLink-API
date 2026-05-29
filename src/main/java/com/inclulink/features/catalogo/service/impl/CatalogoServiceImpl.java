package com.inclulink.features.catalogo.service.impl;

import com.inclulink.core.entidades.*;
import com.inclulink.features.catalogo.repository.*;
import com.inclulink.features.catalogo.service.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CatalogoServiceImpl implements CatalogoService {

    @Autowired private PaisRepository paisRepository;
    @Autowired private CiudadRepository ciudadRepository;
    @Autowired private SectorRepository sectorRepository;
    @Autowired private AccesibilidadRepository accesibilidadRepository;
    @Autowired private EstadoVacanteRepository estadoVacanteRepository;
    @Autowired private JornadaRepository jornadaRepository;
    @Autowired private ModalidadRepository modalidadRepository;
    @Autowired private NivelEstudiosRepository nivelEstudiosRepository;
    @Autowired private IdiomaRepository idiomaRepository;
    @Autowired private NivelIdiomaRepository nivelIdiomaRepository;
    @Autowired private HabilidadRepository habilidadRepository;
    @Autowired private TipoHabilidadRepository tipoHabilidadRepository;
    @Autowired private NivelHabilidadRepository nivelHabilidadRepository;
    @Autowired private DiscapacidadRepository discapacidadRepository;
    @Autowired private TipoDiscapacidadRepository tipoDiscapacidadRepository;
    @Autowired private NivelDiscapacidadRepository nivelDiscapacidadRepository;
    @Autowired private EstadoPostulacionRepository estadoPostulacionRepository;

    @Override public List<Pais> findAllPaises() { return paisRepository.findAll(); }
    @Override public List<Ciudad> findAllCiudades() { return ciudadRepository.findAll(); }
    @Override public List<Sector> findAllSectores() { return sectorRepository.findAll(); }

    @Override
    public List<Ciudad> findCiudadesPorPais(Long idPais) {
        return ciudadRepository.findByPaisId(idPais);
    }

    @Override public List<Accesibilidad> findAllAccesibilidades() { return accesibilidadRepository.findAll(); }
    @Override public List<EstadoVacante> findAllEstadosVacante() { return estadoVacanteRepository.findAll(); }
    @Override public List<Jornada> findAllJornadas() { return jornadaRepository.findAll(); }
    @Override public List<Modalidad> findAllModalidades() { return modalidadRepository.findAll(); }

    @Override public List<NivelEstudios> findAllNivelesEstudios() { return nivelEstudiosRepository.findAll(); }
    @Override public List<Idioma> findAllIdiomas() { return idiomaRepository.findAll(); }
    @Override public List<NivelIdioma> findAllNivelesIdioma() { return nivelIdiomaRepository.findAll(); }
    @Override public List<Habilidad> findAllHabilidades() { return habilidadRepository.findAll(); }
    @Override public List<TipoHabilidad> findAllTiposHabilidad() { return tipoHabilidadRepository.findAll(); }
    @Override public List<NivelHabilidad> findAllNivelesHabilidad() { return nivelHabilidadRepository.findAll(); }

    @Override
    public List<Habilidad> findHabilidadesPorTipo(Long idTipoHabilidad) {
        return habilidadRepository.findByTipoHabilidadId(idTipoHabilidad);
    }

    @Override public List<Discapacidad> findAllDiscapacidades() { return discapacidadRepository.findAll(); }
    @Override public List<TipoDiscapacidad> findAllTiposDiscapacidad() { return tipoDiscapacidadRepository.findAll(); }
    @Override public List<NivelDiscapacidad> findAllNivelesDiscapacidad() { return nivelDiscapacidadRepository.findAll(); }

    @Override
    public List<Discapacidad> findDiscapacidadesPorTipo(Long idTipoDiscapacidad) {
        return discapacidadRepository.findByTipoDiscapacidadId(idTipoDiscapacidad);
    }

    @Override public List<EstadoPostulacion> findAllEstadosPostulacion() { return estadoPostulacionRepository.findAll(); }
}