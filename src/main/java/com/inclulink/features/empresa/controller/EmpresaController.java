package com.inclulink.features.empresa.controller;

import com.inclulink.core.entidades.Empresa;
import com.inclulink.core.services.CloudinaryService.CloudinaryService;
import com.inclulink.features.catalogo.repository.CiudadRepository;
import com.inclulink.features.catalogo.repository.SectorRepository;
import com.inclulink.features.empresa.dto.EmpresaDTO;
import com.inclulink.features.empresa.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/empresas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private SectorRepository sectorRepository;

    @GetMapping("/empresa")
    @ResponseStatus(HttpStatus.OK)
    public List<Empresa> findAll(){
        return empresaService.findAll();
    }

    @GetMapping("/empresa/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Empresa findById(@PathVariable Long id){
        return empresaService.findById(id);
    }

    @PostMapping("/empresa")
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa create(@ModelAttribute EmpresaDTO dto){
        Empresa empresa = new Empresa();

        empresa.setNombre(dto.getNombre());
        empresa.setRfc(dto.getRfc());
        empresa.setCorreo(dto.getCorreo());
        empresa.setContrasena(dto.getContrasena());
        empresa.setTelefono(dto.getTelefono());
        empresa.setDireccion(dto.getDireccion());
        empresa.setPaginaWeb(dto.getPaginaWeb());
        empresa.setDescripcion(dto.getDescripcion());
        empresa.setFechaRegistro(LocalDate.now());
        empresa.setEstaActiva(dto.getEstaActiva() != null ? dto.getEstaActiva() : true);
        empresa.setEsVerificada(dto.getEsVerificada() != null ? dto.getEsVerificada() : false);

        if (dto.getIdCiudad() != null) {
            empresa.setCiudad(ciudadRepository.findById(dto.getIdCiudad()).orElse(null));
        }
        if (dto.getIdSector() != null) {
            empresa.setSector(sectorRepository.findById(dto.getIdSector()).orElse(null));
        }

        try {
            if (dto.getLogoArchivo() != null && !dto.getLogoArchivo().isEmpty()) {
                empresa.setLogo(cloudinaryService.subirArchivo(dto.getLogoArchivo()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al subir el logo a Cloudinary", e);
        }

        return empresaService.save(empresa);
    }

    @PutMapping("/empresa/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa update(@ModelAttribute EmpresaDTO dto, @PathVariable Long id){
        Empresa e = empresaService.findById(id);

        if (e != null) {
            e.setNombre(dto.getNombre());
            e.setRfc(dto.getRfc());
            e.setCorreo(dto.getCorreo());
            e.setTelefono(dto.getTelefono());
            e.setDireccion(dto.getDireccion());
            e.setPaginaWeb(dto.getPaginaWeb());
            e.setDescripcion(dto.getDescripcion());
            if (dto.getEstaActiva() != null) e.setEstaActiva(dto.getEstaActiva());
            if (dto.getEsVerificada() != null) e.setEsVerificada(dto.getEsVerificada());

            if (dto.getIdCiudad() != null) {
                e.setCiudad(ciudadRepository.findById(dto.getIdCiudad()).orElse(null));
            }
            if (dto.getIdSector() != null) {
                e.setSector(sectorRepository.findById(dto.getIdSector()).orElse(null));
            }

            try {
                if (dto.getLogoArchivo() != null && !dto.getLogoArchivo().isEmpty()) {
                    e.setLogo(cloudinaryService.subirArchivo(dto.getLogoArchivo()));
                }
            } catch (IOException ex) {
                throw new RuntimeException("Error al actualizar el logo en Cloudinary", ex);
            }

            return empresaService.save(e);
        }

        return null;
    }

    @DeleteMapping("/empresa/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        empresaService.deleteById(id);
    }
}