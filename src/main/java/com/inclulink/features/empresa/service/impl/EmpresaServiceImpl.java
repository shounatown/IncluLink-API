package com.inclulink.features.empresa.service.impl;

import com.inclulink.core.entidades.Empresa;
import com.inclulink.features.empresa.repository.EmpresaRepository;
import com.inclulink.features.empresa.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Empresa findById(Long id) {
        return empresaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        if (empresa != null) {
            empresa.setEstaActiva(false);
            empresaRepository.save(empresa);
        }
    }
}