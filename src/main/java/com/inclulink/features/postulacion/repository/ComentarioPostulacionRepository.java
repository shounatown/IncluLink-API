package com.inclulink.features.postulacion.repository;

import com.inclulink.core.entidades.ComentarioPostulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioPostulacionRepository extends JpaRepository<ComentarioPostulacion, Long> {

    List<ComentarioPostulacion> findByPostulacionIdOrderByIdDesc(Long idPostulacion);
}