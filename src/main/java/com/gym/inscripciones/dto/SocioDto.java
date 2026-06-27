package com.gym.inscripciones.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocioDto {
    private String run;
    private String nombre;
    private String apellido;
    private String correo;     
    private String estadoPlan; 
}