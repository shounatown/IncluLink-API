package com.inclulink.features.catalogo.service;

import com.inclulink.core.entidades.*;
import java.util.List;

public interface CatalogoService {

    List<Pais> findAllPaises();
    List<Ciudad> findAllCiudades();
    List<Ciudad> findCiudadesPorPais(Long idPais);
    List<Sector> findAllSectores();

    List<Accesibilidad> findAllAccesibilidades();
    List<EstadoVacante> findAllEstadosVacante();
    List<Jornada> findAllJornadas();
    List<Modalidad> findAllModalidades();

    List<NivelEstudios> findAllNivelesEstudios();
    List<Idioma> findAllIdiomas();
    List<NivelIdioma> findAllNivelesIdioma();
    List<Habilidad> findAllHabilidades();
    List<Habilidad> findHabilidadesPorTipo(Long idTipoHabilidad);
    List<TipoHabilidad> findAllTiposHabilidad();
    List<NivelHabilidad> findAllNivelesHabilidad();

    List<Discapacidad> findAllDiscapacidades();
    List<Discapacidad> findDiscapacidadesPorTipo(Long idTipoDiscapacidad);
    List<TipoDiscapacidad> findAllTiposDiscapacidad();
    List<NivelDiscapacidad> findAllNivelesDiscapacidad();

    List<EstadoPostulacion> findAllEstadosPostulacion();
}