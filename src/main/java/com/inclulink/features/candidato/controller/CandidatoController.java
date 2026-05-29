package com.inclulink.features.candidato.controller;

import com.inclulink.core.entidades.Candidato;
import com.inclulink.core.services.CloudinaryService.CloudinaryService;
import com.inclulink.features.candidato.dto.CandidatoDTO;
import com.inclulink.features.candidato.service.CandidatoService;
import com.inclulink.features.catalogo.repository.CiudadRepository;
import com.inclulink.features.catalogo.repository.NivelEstudiosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/candidatos")
@RequiredArgsConstructor
public class CandidatoController {

    private final CandidatoService candidatoService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private NivelEstudiosRepository nivelEstudiosRepository;

    @GetMapping("/candidato")
    @ResponseStatus(HttpStatus.OK)
    public List<Candidato> findAll(){
        return candidatoService.findAll();
    }

    @GetMapping("/candidato/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Candidato findById(@PathVariable Long id){
        return candidatoService.findById(id);
    }

    @PostMapping("/candidato")
    @ResponseStatus(HttpStatus.CREATED)
    public Candidato create(@ModelAttribute CandidatoDTO dto){

        Candidato candidato = new Candidato();

        candidato.setNombre(dto.getNombre());
        candidato.setCorreo(dto.getCorreo());
        candidato.setContrasena(dto.getContrasena());
        candidato.setTelefono(dto.getTelefono());
        candidato.setDireccion(dto.getDireccion());
        candidato.setRfc(dto.getRfc());
        candidato.setFechaNacimiento(dto.getFechaNacimiento());
        candidato.setAnosExperiencia(dto.getAnosExperiencia());
        candidato.setDescripcion(dto.getDescripcion());
        candidato.setGithub(dto.getGithub());
        candidato.setFechaRegistro(LocalDate.now());
        candidato.setEstaActivo(dto.getEstaActivo());

        if (dto.getIdCiudad() != null) {
            candidato.setCiudad(ciudadRepository.findById(dto.getIdCiudad()).orElse(null));
        }
        if (dto.getIdNivelEstudios() != null) {
            candidato.setNivelEstudios(nivelEstudiosRepository.findById(dto.getIdNivelEstudios()).orElse(null));
        }

        try {
            if (dto.getFotoArchivo() != null && !dto.getFotoArchivo().isEmpty()) {
                candidato.setFoto(cloudinaryService.subirArchivo(dto.getFotoArchivo()));
            }
            if (dto.getCurriculumArchivo() != null && !dto.getCurriculumArchivo().isEmpty()) {
                candidato.setCurriculum(cloudinaryService.subirArchivo(dto.getCurriculumArchivo()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al subir archivos a Cloudinary", e);
        }

        return candidatoService.save(candidato);
    }

    @PutMapping("/candidato/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Candidato update(@ModelAttribute CandidatoDTO dto, @PathVariable Long id){
        Candidato c = candidatoService.findById(id);

        if (c != null) {
            c.setNombre(dto.getNombre());
            c.setCorreo(dto.getCorreo());
            c.setTelefono(dto.getTelefono());
            c.setDireccion(dto.getDireccion());
            c.setAnosExperiencia(dto.getAnosExperiencia());
            c.setDescripcion(dto.getDescripcion());
            c.setGithub(dto.getGithub());
            c.setEstaActivo(dto.getEstaActivo());

            if (dto.getIdCiudad() != null) {
                c.setCiudad(ciudadRepository.findById(dto.getIdCiudad()).orElse(null));
            }
            if (dto.getIdNivelEstudios() != null) {
                c.setNivelEstudios(nivelEstudiosRepository.findById(dto.getIdNivelEstudios()).orElse(null));
            }

            try {
                if (dto.getFotoArchivo() != null && !dto.getFotoArchivo().isEmpty()) {
                    c.setFoto(cloudinaryService.subirArchivo(dto.getFotoArchivo()));
                }
                if (dto.getCurriculumArchivo() != null && !dto.getCurriculumArchivo().isEmpty()) {
                    c.setCurriculum(cloudinaryService.subirArchivo(dto.getCurriculumArchivo()));
                }
            } catch (IOException e) {
                throw new RuntimeException("Error al actualizar archivos en Cloudinary", e);
            }

            return candidatoService.save(c);
        }

        return null;
    }

    @DeleteMapping("/candidato/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        candidatoService.deleteById(id);
    }
}