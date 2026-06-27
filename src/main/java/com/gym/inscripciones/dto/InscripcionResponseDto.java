package com.gym.inscripciones.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionResponseDto {
    private Long id;
    private LocalDate fechaInscripcion;

    private String runSocio;
    private String nombreSocio;
    private String emailSocio;

   
    private Long idClase;
    private String nombreClase;
    private String horarioClase;
}