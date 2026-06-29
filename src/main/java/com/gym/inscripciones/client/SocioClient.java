package com.gym.inscripciones.client;

import com.gym.inscripciones.dto.SocioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${FEIGN_URL_SOCIOS:http://localhost:8080}")
public interface SocioClient {

    @GetMapping("/api/socios/buscar/{run}") 
    SocioDto obtenerSocioPorRun(@PathVariable("run") String run);
}