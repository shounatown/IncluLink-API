package com.inclulink.features.candidato.service.impl;


import com.inclulink.features.candidato.repository.CandidatoRepository;
import com.inclulink.features.candidato.service.CandidatoService;
import com.inclulink.core.entidades.Candidato;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CandidatoServiceImpl implements CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Candidato> findAll() {
        return candidatoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Candidato findById(Long id) {
        return candidatoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Candidato save(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Candidato candidato = candidatoRepository.findById(id).orElse(null);
        if (candidato != null) {
            candidato.setEstaActivo(false);
            candidatoRepository.save(candidato);
        }
    }
}