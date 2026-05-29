package com.inclulink.features.empresa.service;

import com.inclulink.core.entidades.Empresa;
import java.util.List;

public interface EmpresaService {
    List<Empresa> findAll();
    Empresa findById(Long id);
    Empresa save(Empresa empresa);
    void deleteById(Long id);
}