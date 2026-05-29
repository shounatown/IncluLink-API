package com.inclulink.features.postulacion.repository;

import com.inclulink.core.entidades.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostulacionRepository extends JpaRepository<Postulacion, Long> {

    List<Postulacion> findByCandidatoId(Long idCandidato);
    List<Postulacion> findByVacanteId(Long idVacante);
    boolean existsByCandidatoIdAndVacanteId(Long idCandidato, Long idVacante);
}