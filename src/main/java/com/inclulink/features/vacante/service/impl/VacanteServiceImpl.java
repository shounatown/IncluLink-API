package com.inclulink.features.vacante.service.impl;

import com.inclulink.core.entidades.Vacante;
import com.inclulink.features.vacante.repository.VacanteRepository;
import com.inclulink.features.vacante.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VacanteServiceImpl implements VacanteService {

    @Autowired
    private VacanteRepository vacanteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Vacante> findAll() {
        return vacanteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Vacante findById(Long id) {
        return vacanteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Vacante save(Vacante vacante) {
        return vacanteRepository.save(vacante);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        vacanteRepository.deleteById(id);
    }



    //EXTRA
    @Override
    @Transactional(readOnly = true)
    public List<Vacante> findByEmpresa(Long idEmpresa) {
        return vacanteRepository.findByEmpresaId(idEmpresa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vacante> findByCiudad(Long idCiudad) {
        return vacanteRepository.findByCiudadId(idCiudad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vacante> findByName(String palabraClave) {
        return vacanteRepository.findByNombreContainingIgnoreCase(palabraClave);
    }
}