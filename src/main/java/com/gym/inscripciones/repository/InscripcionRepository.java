package com.gym.inscripciones.repository;

import com.gym.inscripciones.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {


    List<Inscripcion> findByRunSocio(String runSocio);

   
    long countByIdClase(Long idClase);

    boolean existsByRunSocioAndIdClase(String runSocio, Long idClase);
}