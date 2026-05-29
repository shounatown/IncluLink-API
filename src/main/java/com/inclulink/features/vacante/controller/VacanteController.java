package com.inclulink.features.vacante.controller;

import com.inclulink.core.entidades.Vacante;
import com.inclulink.core.services.CloudinaryService.CloudinaryService;
import com.inclulink.features.catalogo.repository.*;
import com.inclulink.features.empresa.repository.EmpresaRepository;
import com.inclulink.features.vacante.dto.VacanteDTO;
import com.inclulink.features.vacante.service.VacanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vacantes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class VacanteController {

    private final VacanteService vacanteService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private EstadoVacanteRepository estadoVacanteRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private ModalidadRepository modalidadRepository;

    @Autowired
    private JornadaRepository jornadaRepository;

    @Autowired
    private AccesibilidadRepository accesibilidadRepository;


    @GetMapping("/vacante")
    @ResponseStatus(HttpStatus.OK)
    public List<Vacante> findAll(){
        return vacanteService.findAll();
    }

    @GetMapping("/vacante/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vacante findById(@PathVariable Long id){
        return vacanteService.findById(id);
    }

    @GetMapping("/empresa/{idEmpresa}")
    @ResponseStatus(HttpStatus.OK)
    public List<Vacante> findByEmpresa(@PathVariable Long idEmpresa) {
        return vacanteService.findByEmpresa(idEmpresa);
    }

    @GetMapping("/ciudad/{idCiudad}")
    @ResponseStatus(HttpStatus.OK)
    public List<Vacante> findByCiudad(@PathVariable Long idCiudad) {
        return vacanteService.findByCiudad(idCiudad);
    }

    @GetMapping("/buscar")
    @ResponseStatus(HttpStatus.OK)
    public List<Vacante> buscarPorNombre(@RequestParam String nombre) {
        return vacanteService.findByName(nombre);
    }

    @PostMapping("/vacante")
    @ResponseStatus(HttpStatus.CREATED)
    public Vacante create(@ModelAttribute VacanteDTO dto){
        Vacante vacante = new Vacante();

        vacante.setNombre(dto.getNombre());
        vacante.setDescripcion(dto.getDescripcion());
        vacante.setRequisitos(dto.getRequisitos());
        vacante.setSalarioMaximo(dto.getSalarioMaximo());
        vacante.setSalarioMinimo(dto.getSalarioMinimo());
        vacante.setAnosExperiencia(dto.getAnosExperiencia());
        vacante.setFechaPublicacion(LocalDate.now());
        vacante.setFechaCierre(dto.getFechaCierre());
        vacante.setCupo(dto.getCupo());
        vacante.setVistasContador(0);

        if (dto.getIdEstadoVacante() != null) {
            vacante.setEstadoVacante(estadoVacanteRepository.findById(dto.getIdEstadoVacante()).orElse(null));
        }
        if (dto.getIdEmpresa() != null) {
            vacante.setEmpresa(empresaRepository.findById(dto.getIdEmpresa()).orElse(null));
        }
        if (dto.getIdCiudad() != null) {
            vacante.setCiudad(ciudadRepository.findById(dto.getIdCiudad()).orElse(null));
        }
        if (dto.getIdModalidad() != null) {
            vacante.setModalidad(modalidadRepository.findById(dto.getIdModalidad()).orElse(null));
        }
        if (dto.getIdJornada() != null) {
            vacante.setJornada(jornadaRepository.findById(dto.getIdJornada()).orElse(null));
        }

        if (dto.getIdAccesibilidades() != null && !dto.getIdAccesibilidades().isEmpty()) {
            vacante.setAccesibilidades(
                    dto.getIdAccesibilidades().stream()
                            .map(idAcc -> accesibilidadRepository.findById(idAcc).orElse(null))
                            .filter(acc -> acc != null)
                            .collect(Collectors.toList())
            );
        }

        try {
            if (dto.getFotoArchivo() != null && !dto.getFotoArchivo().isEmpty()) {
                vacante.setFoto(cloudinaryService.subirArchivo(dto.getFotoArchivo()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al subir la foto de la vacante a Cloudinary", e);
        }

        return vacanteService.save(vacante);
    }

    @PutMapping("/vacante/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Vacante update(@ModelAttribute VacanteDTO dto, @PathVariable Long id){
        Vacante v = vacanteService.findById(id);

        if (v != null) {
            v.setNombre(dto.getNombre());
            v.setDescripcion(dto.getDescripcion());
            v.setRequisitos(dto.getRequisitos());
            v.setSalarioMaximo(dto.getSalarioMaximo());
            v.setSalarioMinimo(dto.getSalarioMinimo());
            v.setAnosExperiencia(dto.getAnosExperiencia());
            v.setFechaCierre(dto.getFechaCierre());
            v.setCupo(dto.getCupo());
            if (dto.getVistasContador() != null) {
                v.setVistasContador(dto.getVistasContador());
            }

            if (dto.getIdEstadoVacante() != null) {
                v.setEstadoVacante(estadoVacanteRepository.findById(dto.getIdEstadoVacante()).orElse(null));
            }
            if (dto.getIdEmpresa() != null) {
                v.setEmpresa(empresaRepository.findById(dto.getIdEmpresa()).orElse(null));
            }
            if (dto.getIdCiudad() != null) {
                v.setCiudad(ciudadRepository.findById(dto.getIdCiudad()).orElse(null));
            }
            if (dto.getIdModalidad() != null) {
                v.setModalidad(modalidadRepository.findById(dto.getIdModalidad()).orElse(null));
            }
            if (dto.getIdJornada() != null) {
                v.setJornada(jornadaRepository.findById(dto.getIdJornada()).orElse(null));
            }

            if (dto.getIdAccesibilidades() != null) {
                v.setAccesibilidades(
                        dto.getIdAccesibilidades().stream()
                                .map(idAcc -> accesibilidadRepository.findById(idAcc).orElse(null))
                                .filter(acc -> acc != null)
                                .collect(Collectors.toList())
                );
            }

            try {
                if (dto.getFotoArchivo() != null && !dto.getFotoArchivo().isEmpty()) {
                    v.setFoto(cloudinaryService.subirArchivo(dto.getFotoArchivo()));
                }
            } catch (IOException e) {
                throw new RuntimeException("Error al actualizar la foto en Cloudinary", e);
            }

            return vacanteService.save(v);
        }

        return null;
    }

    @DeleteMapping("/vacante/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        vacanteService.deleteById(id);
    }
}