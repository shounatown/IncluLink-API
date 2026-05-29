package com.inclulink.features.candidato.service;

import com.inclulink.core.entidades.Candidato;
import com.inclulink.features.candidato.dto.CandidatoDTO;

import java.util.List;

public interface CandidatoService {
    List<Candidato> findAll();
    Candidato findById(Long id);
    Candidato save(Candidato candidato);
    void deleteById(Long id);
}
