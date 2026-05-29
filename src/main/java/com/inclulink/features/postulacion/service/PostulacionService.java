package com.inclulink.features.postulacion.service;

import com.inclulink.core.entidades.ComentarioPostulacion;
import com.inclulink.core.entidades.Postulacion;
import com.inclulink.features.postulacion.dto.ComentarioPostulacionDTO;

import java.util.List;

public interface PostulacionService {
    List<Postulacion> findAll();
    Postulacion findById(Long id);
    Postulacion save(Postulacion postulacion);
    void deleteById(Long id);

    List<Postulacion> findByCandidato(Long idCandidato);
    List<Postulacion> findByVacante(Long idVacante);
    boolean alreadyApplied(Long idCandidato, Long idVacante);

    ComentarioPostulacion agregarComentario(Long idPostulacion, ComentarioPostulacionDTO dto);
}
