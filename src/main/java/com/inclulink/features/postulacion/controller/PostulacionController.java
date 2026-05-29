package com.inclulink.features.postulacion.controller;

import com.inclulink.core.entidades.ComentarioPostulacion;
import com.inclulink.core.entidades.Postulacion;
import com.inclulink.features.candidato.repository.CandidatoRepository;
import com.inclulink.features.catalogo.repository.EstadoPostulacionRepository;
import com.inclulink.features.postulacion.dto.ComentarioPostulacionDTO;
import com.inclulink.features.postulacion.repository.ComentarioPostulacionRepository;
import com.inclulink.features.vacante.repository.VacanteRepository;
import com.inclulink.features.postulacion.dto.PostulacionDTO;
import com.inclulink.features.postulacion.service.PostulacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/postulaciones")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PostulacionController {

    private final PostulacionService postulacionService;

    @Autowired
    private VacanteRepository vacanteRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private EstadoPostulacionRepository estadoPostulacionRepository;

    @Autowired
    private ComentarioPostulacionRepository comentarioPostulacionRepository;


    @GetMapping("/postulacion")
    @ResponseStatus(HttpStatus.OK)
    public List<Postulacion> findAll(){
        return postulacionService.findAll();
    }

    @GetMapping("/postulacion/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Postulacion findById(@PathVariable Long id){
        return postulacionService.findById(id);
    }

    @GetMapping("/candidato/{idCandidato}")
    @ResponseStatus(HttpStatus.OK)
    public List<Postulacion> findByCandidato(@PathVariable Long idCandidato) {
        return postulacionService.findByCandidato(idCandidato);
    }

    @GetMapping("/vacante/{idVacante}")
    @ResponseStatus(HttpStatus.OK)
    public List<Postulacion> findByVacante(@PathVariable Long idVacante) {
        return postulacionService.findByVacante(idVacante);
    }


    @PostMapping("/postulacion")
    @ResponseStatus(HttpStatus.CREATED)
    public Postulacion create(@RequestBody PostulacionDTO dto){
        if (postulacionService.alreadyApplied(dto.getIdCandidato(), dto.getIdVacante())) {
            throw new RuntimeException("El candidato ya se encuentra postulado a esta vacante.");
        }

        Postulacion postulacion = new Postulacion();
        postulacion.setFechaPostulacion(LocalDate.now());

        if (dto.getIdVacante() != null) {
            postulacion.setVacante(vacanteRepository.findById(dto.getIdVacante()).orElse(null));
        }
        if (dto.getIdCandidato() != null) {
            postulacion.setCandidato(candidatoRepository.findById(dto.getIdCandidato()).orElse(null));
        }
        if (dto.getIdEstadoPostulacion() != null) {
            postulacion.setEstadoPostulacion(estadoPostulacionRepository.findById(dto.getIdEstadoPostulacion()).orElse(null));
        }

        return postulacionService.save(postulacion);
    }

    @PutMapping("/postulacion/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Postulacion update(@RequestBody PostulacionDTO dto, @PathVariable Long id){
        Postulacion p = postulacionService.findById(id);

        if (p != null) {
            p.setFechaActualizacion(LocalDate.now());

            if (dto.getIdEstadoPostulacion() != null) {
                p.setEstadoPostulacion(estadoPostulacionRepository.findById(dto.getIdEstadoPostulacion()).orElse(null));
            }

            return postulacionService.save(p);
        }

        return null;
    }

    @DeleteMapping("/postulacion/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        postulacionService.deleteById(id);
    }


    @PostMapping("/postulacion/{idPostulacion}/comentarios")
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioPostulacion agregarComentario(
            @PathVariable Long idPostulacion,
            @RequestBody ComentarioPostulacionDTO dto) {
        Postulacion postulacion = postulacionService.findById(idPostulacion);
        if (postulacion == null) {
            throw new RuntimeException("No se encontró la postulación con ID: " + idPostulacion);
        }

        ComentarioPostulacion nuevoComentario = new ComentarioPostulacion();
        nuevoComentario.setTexto(dto.getComentario());
        nuevoComentario.setFechaCreacion(java.time.LocalDateTime.now());
        nuevoComentario.setPostulacion(postulacion);

        return comentarioPostulacionRepository.save(nuevoComentario);
    }


    @DeleteMapping("/comentarios/{idComentario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarComentario(@PathVariable Long idComentario) {
        if (!comentarioPostulacionRepository.existsById(idComentario)) {
            throw new RuntimeException("No existe el comentario con ID: " + idComentario);
        }
        comentarioPostulacionRepository.deleteById(idComentario);
    }
}