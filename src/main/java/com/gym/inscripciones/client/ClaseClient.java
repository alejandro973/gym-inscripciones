package com.gym.inscripciones.client;

import com.gym.inscripciones.dto.ClaseDto; 
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clases", url = "http://localhost:8083/api/clases")
public interface ClaseClient {

    @GetMapping("/{id}")
    ClaseDto obtenerClasePorId(@PathVariable Long id);
}