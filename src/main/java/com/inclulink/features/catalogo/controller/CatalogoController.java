package com.inclulink.features.catalogo.controller;

import com.inclulink.features.catalogo.dto.CatalogoDTO;
import com.inclulink.features.catalogo.dto.CatalogoDependienteDTO;
import com.inclulink.features.catalogo.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalogo")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CatalogoController {

    private final CatalogoService catalogoService;

    @GetMapping("/paises")
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogoDTO> findAllPais() {
        return catalogoService.findAllPaises();
    }

    @GetMapping("/modalidades")
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogoDTO> findAllModalidad() {
        return catalogoService.findAllModalidades();
    }

    @GetMapping("/estados-vacante")
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogoDTO> findAllEstadoVacante() {
        return catalogoService.findAllEstadosVacante();
    }

    @GetMapping("/sectores")
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogoDTO> findAllSector() {
        return catalogoService.findAllSectores();
    }

    @GetMapping("/tipos-discapacidad")
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogoDTO> findAllTipoDiscapacidad() {
        return catalogoService.findAllTiposDiscapacidad();
    }

    @GetMapping("/discapacidades")
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogoDTO> findAllDiscapacidad() {
        return catalogoService.findAllDiscapacidades();
    }

    @GetMapping("/tipos-habilidad")
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogoDTO> findAllTipoHabilidad() {
        return catalogoService.findAllTiposHabilidad();
    }

    @GetMapping("/habilidades")
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogoDTO> findAllHabilidad() {
        return catalogoService.findAllHabilidades();
    }

    @GetMapping("/paises/{paisId}/ciudades")
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogoDependienteDTO> findCiudadByPais(@PathVariable Long paisId) {
        return catalogoService.findCiudadByPais(paisId);
    }

    @GetMapping("/tipos-habilidad/{tipoId}/habilidades")
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogoDependienteDTO> findHabilidadByTipo(@PathVariable Long tipoId) {
        return catalogoService.findHabilidadByTipo(tipoId);
    }

    @GetMapping("/tipos-discapacidad/{tipoId}/discapacidades")
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogoDependienteDTO> findDiscapacidadByTipo(@PathVariable Long tipoId) {
        return catalogoService.findDiscapacidadByTipo(tipoId);
    }
}