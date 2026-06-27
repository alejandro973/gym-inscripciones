package com.gym.inscripciones.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaseDto {
    private Long id;
    private String nombreClase;
    private String horario;
    private Integer cuposMaximos;
}