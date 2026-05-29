package com.inclulink.features.catalogo.controller;

import com.inclulink.core.entidades.*;
import com.inclulink.features.catalogo.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalogos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CatalogoController {

    private final CatalogoService catalogoService;

    @GetMapping("/paises")
    @ResponseStatus(HttpStatus.OK)
    public List<Pais> getPaises() {
        return catalogoService.findAllPaises();
    }

    @GetMapping("/ciudades")
    @ResponseStatus(HttpStatus.OK)
    public List<Ciudad> getCiudades() {
        return catalogoService.findAllCiudades();
    }

    @GetMapping("/paises/{idPais}/ciudades")
    @ResponseStatus(HttpStatus.OK)
    public List<Ciudad> getCiudadesPorPais(@PathVariable Long idPais) {
        return catalogoService.findCiudadesPorPais(idPais);
    }

    @GetMapping("/sectores")
    @ResponseStatus(HttpStatus.OK)
    public List<Sector> getSectores() {
        return catalogoService.findAllSectores();
    }

    @GetMapping("/accesibilidades")
    @ResponseStatus(HttpStatus.OK)
    public List<Accesibilidad> getAccesibilidades() {
        return catalogoService.findAllAccesibilidades();
    }

    @GetMapping("/estados-vacante")
    @ResponseStatus(HttpStatus.OK)
    public List<EstadoVacante> getEstadosVacante() {
        return catalogoService.findAllEstadosVacante();
    }

    @GetMapping("/jornadas")
    @ResponseStatus(HttpStatus.OK)
    public List<Jornada> getJornadas() {
        return catalogoService.findAllJornadas();
    }

    @GetMapping("/modalidades")
    @ResponseStatus(HttpStatus.OK)
    public List<Modalidad> getModalidades() {
        return catalogoService.findAllModalidades();
    }


    @GetMapping("/niveles-estudios")
    @ResponseStatus(HttpStatus.OK)
    public List<NivelEstudios> getNivelesEstudios() {
        return catalogoService.findAllNivelesEstudios();
    }

    @GetMapping("/idiomas")
    @ResponseStatus(HttpStatus.OK)
    public List<Idioma> getIdiomas() {
        return catalogoService.findAllIdiomas();
    }

    @GetMapping("/niveles-idioma")
    @ResponseStatus(HttpStatus.OK)
    public List<NivelIdioma> getNivelesIdioma() {
        return catalogoService.findAllNivelesIdioma();
    }

    @GetMapping("/habilidades")
    @ResponseStatus(HttpStatus.OK)
    public List<Habilidad> getHabilidades() {
        return catalogoService.findAllHabilidades();
    }

    @GetMapping("/tipos-habilidad/{idTipoHabilidad}/habilidades")
    @ResponseStatus(HttpStatus.OK)
    public List<Habilidad> getHabilidadesPorTipo(@PathVariable Long idTipoHabilidad) {
        return catalogoService.findHabilidadesPorTipo(idTipoHabilidad);
    }

    @GetMapping("/tipos-habilidad")
    @ResponseStatus(HttpStatus.OK)
    public List<TipoHabilidad> getTiposHabilidad() {
        return catalogoService.findAllTiposHabilidad();
    }

    @GetMapping("/niveles-habilidad")
    @ResponseStatus(HttpStatus.OK)
    public List<NivelHabilidad> getNivelesHabilidad() {
        return catalogoService.findAllNivelesHabilidad();
    }


    @GetMapping("/discapacidades")
    @ResponseStatus(HttpStatus.OK)
    public List<Discapacidad> getDiscapacidades() {
        return catalogoService.findAllDiscapacidades();
    }

    @GetMapping("/tipos-discapacidad/{idTipoDiscapacidad}/discapacidades")
    @ResponseStatus(HttpStatus.OK)
    public List<Discapacidad> getDiscapacidadesPorTipo(@PathVariable Long idTipoDiscapacidad) {
        return catalogoService.findDiscapacidadesPorTipo(idTipoDiscapacidad);
    }

    @GetMapping("/tipos-discapacidad")
    @ResponseStatus(HttpStatus.OK)
    public List<TipoDiscapacidad> getTiposDiscapacidad() {
        return catalogoService.findAllTiposDiscapacidad();
    }

    @GetMapping("/niveles-discapacidad")
    @ResponseStatus(HttpStatus.OK)
    public List<NivelDiscapacidad> getNivelesDiscapacidad() {
        return catalogoService.findAllNivelesDiscapacidad();
    }


    @GetMapping("/estados-postulacion")
    @ResponseStatus(HttpStatus.OK)
    public List<EstadoPostulacion> getEstadosPostulacion() {
        return catalogoService.findAllEstadosPostulacion();
    }
}