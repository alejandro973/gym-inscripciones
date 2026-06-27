package com.gym.inscripciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "inscripciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank 
@Column(nullable = false)
private String runSocio;

@NotNull
@Column(nullable = false) 
private Long idClase;

@NotNull
private LocalDate fechaInscripcion;

@PrePersist
public void prePersist() {
    this.fechaInscripcion = LocalDate.now();
}

}