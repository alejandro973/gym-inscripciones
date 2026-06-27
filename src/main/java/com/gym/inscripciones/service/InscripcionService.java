package com.gym.inscripciones.service;

import com.gym.inscripciones.client.ClaseClient;
import com.gym.inscripciones.client.SocioClient;
import com.gym.inscripciones.dto.*;
import com.gym.inscripciones.model.Inscripcion;
import com.gym.inscripciones.repository.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final SocioClient socioClient;
    private final ClaseClient claseClient;

    private InscripcionResponseDto mapToDto(Inscripcion i) {
        InscripcionResponseDto dto = new InscripcionResponseDto();
        dto.setId(i.getId());
        dto.setFechaInscripcion(i.getFechaInscripcion());
        dto.setRunSocio(i.getRunSocio());
        dto.setIdClase(i.getIdClase());

        dto.setNombreSocio("Socio no encontrado");
        dto.setNombreClase("Clase no encontrada");

        try {
            SocioDto socio = socioClient.obtenerSocioPorRun(i.getRunSocio());
            if (socio != null) {
                dto.setNombreSocio(socio.getNombre() + " " + socio.getApellido());
                dto.setEmailSocio(socio.getCorreo());
            }
        } catch (Exception e) {
            System.err.println("Error llamando a ms-socios: " + e.getMessage());
        }

        try {
            ClaseDto clase = claseClient.obtenerClasePorId(i.getIdClase());
            if (clase != null) {
                dto.setNombreClase(clase.getNombreClase());
                dto.setHorarioClase(clase.getHorario());
            }
        } catch (Exception e) {
            System.err.println("Error llamando a ms-clases: " + e.getMessage());
        }

        return dto;
    }

   
    public List<InscripcionResponseDto> obtenerTodas() {
        return inscripcionRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    
    public InscripcionResponseDto crearInscripcion(InscripcionRequestDto request) {
        
        
        SocioDto socio = socioClient.obtenerSocioPorRun(request.getRunSocio());
        if (socio == null || !"Activo".equalsIgnoreCase(socio.getEstadoPlan())) {
            throw new RuntimeException("El socio no existe o no está activo para inscribirse.");
        }

       
        ClaseDto clase = claseClient.obtenerClasePorId(request.getIdClase());
        if (clase == null) {
            throw new RuntimeException("La clase no existe.");
        }

        long inscritosActuales = inscripcionRepository.countByIdClase(request.getIdClase());
        if (inscritosActuales >= clase.getCuposMaximos()) {
            throw new RuntimeException("No hay cupos disponibles para la clase: " + clase.getNombreClase());
        }

     
        if (inscripcionRepository.existsByRunSocioAndIdClase(request.getRunSocio(), request.getIdClase())) {
            throw new RuntimeException("El socio ya se encuentra inscrito en esta clase.");
        }

       
        Inscripcion nueva = new Inscripcion();
        nueva.setRunSocio(request.getRunSocio());
        nueva.setIdClase(request.getIdClase());
        
        return mapToDto(inscripcionRepository.save(nueva));
    }

    public void cancelarInscripcion(Long id) {
        if (!inscripcionRepository.existsById(id)) {
            throw new RuntimeException("La inscripción no existe.");
        }
        inscripcionRepository.deleteById(id);
    }
}