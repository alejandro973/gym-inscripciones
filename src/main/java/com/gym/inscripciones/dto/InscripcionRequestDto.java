package com.gym.inscripciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionRequestDto {

    @NotBlank(message = "El RUN del socio no puede estar vacío")
    private String runSocio;

    @NotNull(message = "El ID de la clase es obligatorio")
    private Long idClase;
}