package com.gym.inscripciones.config;

import com.gym.inscripciones.model.Inscripcion;
import com.gym.inscripciones.repository.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final InscripcionRepository repository;

    @Override
    public void run(String... args) {
        
        if (repository.count() > 0) {
            log.info(">>> La base de datos de Inscripciones ya tiene información. Saltando carga.");
            return;
        }

        log.info(">>> Iniciando carga de inscripciones de prueba...");

      
        repository.save(new Inscripcion(null, "11111111-1", 1L, null));
        repository.save(new Inscripcion(null, "22222222-2", 2L, null));
        
        repository.save(new Inscripcion(null, "33333333-3", 1L, null)); 

        log.info(">>> ¡Carga de Inscripciones finalizada con éxito!");
    }
}