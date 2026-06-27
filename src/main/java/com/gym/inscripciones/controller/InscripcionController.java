package com.gym.inscripciones.controller;

import com.gym.inscripciones.dto.InscripcionRequestDto;
import com.gym.inscripciones.dto.InscripcionResponseDto;
import com.gym.inscripciones.service.InscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
@Tag(name = "Inscripciones",description = "Operaciones relacionadas a las inscripciones del gym")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @GetMapping
    @Operation(summary = "Listar Inscripciones",description = "Permite listar todas las inscripciones del gym")
    public ResponseEntity<List<InscripcionResponseDto>> listarInscripciones() {
        return ResponseEntity.ok(inscripcionService.obtenerTodas());
    }

   
    @PostMapping
    @Operation(summary = "Crear Inscripcion",description = "Permite la creacion de una nueva inscripcion")
    public ResponseEntity<?> realizarInscripcion(@Valid @RequestBody InscripcionRequestDto request) {
        try {
            InscripcionResponseDto nueva = inscripcionService.crearInscripcion(request);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (RuntimeException e) {
         
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

   
    @DeleteMapping("/{id}")
    @Operation(summary = "Cancelar Inscripcion",description = "Permite Cancelar la inscripcion por medio de su id")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        try {
            inscripcionService.cancelarInscripcion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}