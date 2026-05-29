package com.inclulink.features.postulacion.service.impl;

import com.inclulink.core.entidades.ComentarioPostulacion;
import com.inclulink.core.entidades.Postulacion;
import com.inclulink.features.postulacion.dto.ComentarioPostulacionDTO;
import com.inclulink.features.postulacion.repository.ComentarioPostulacionRepository;
import com.inclulink.features.postulacion.repository.PostulacionRepository;
import com.inclulink.features.postulacion.service.PostulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostulacionServiceImpl implements PostulacionService {

    @Autowired
    private PostulacionRepository postulacionRepository;

    @Autowired
    private ComentarioPostulacionRepository comentarioPostulacionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Postulacion> findAll() {
        return postulacionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Postulacion findById(Long id) {
        return postulacionRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Postulacion save(Postulacion postulacion) {
        return postulacionRepository.save(postulacion);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        postulacionRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Postulacion> findByCandidato(Long idCandidato) {
        return postulacionRepository.findByCandidatoId(idCandidato);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Postulacion> findByVacante(Long idVacante) {
        return postulacionRepository.findByVacanteId(idVacante);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean alreadyApplied(Long idCandidato, Long idVacante) {
        return postulacionRepository.existsByCandidatoIdAndVacanteId(idCandidato, idVacante);
    }

    @Override
    @Transactional
    public ComentarioPostulacion agregarComentario(Long idPostulacion, ComentarioPostulacionDTO dto) {
        Postulacion postulacion = postulacionRepository.findById(idPostulacion).orElse(null);
        if (postulacion == null) {
            throw new RuntimeException("No se encontró la postulación");
        }

        ComentarioPostulacion nuevoComentario = new ComentarioPostulacion();
        nuevoComentario.setTexto(dto.getComentario());
        nuevoComentario.setFechaCreacion(java.time.LocalDateTime.now());
        nuevoComentario.setPostulacion(postulacion);

        return comentarioPostulacionRepository.save(nuevoComentario);
    }
}