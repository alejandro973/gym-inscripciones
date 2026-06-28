package com.gym.inscripciones;

import com.gym.inscripciones.dto.InscripcionResponseDto;
import com.gym.inscripciones.model.Inscripcion;
import com.gym.inscripciones.repository.InscripcionRepository;
import com.gym.inscripciones.client.SocioClient; 
import com.gym.inscripciones.client.ClaseClient; 
import com.gym.inscripciones.dto.SocioDto;        
import com.gym.inscripciones.dto.ClaseDto;     
import com.gym.inscripciones.service.InscripcionService;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
public class InscripcionServiceTest {

    @Autowired
    private InscripcionService inscripcionService;

    @MockBean
    private InscripcionRepository inscripcionRepository;

    @MockBean
    private SocioClient socioClient;

    @MockBean
    private ClaseClient claseClient;

    @Test
    public void testObtenerTodas() {
        Inscripcion inscripcionFake = new Inscripcion(1L, "12345678-9", 10L, LocalDate.now());
        when(inscripcionRepository.findAll()).thenReturn(List.of(inscripcionFake));

        SocioDto socioFake = new SocioDto();
        socioFake.setNombre("Juan");
        socioFake.setApellido("Perez");
        socioFake.setCorreo("juan@gmail.com");
        when(socioClient.obtenerSocioPorRun("12345678-9")).thenReturn(socioFake);

        // Construimos el ClaseDto usando SET
        ClaseDto claseFake = new ClaseDto();
        claseFake.setNombreClase("Yoga");
        claseFake.setHorario("19:00");
        claseFake.setCuposMaximos(20);
        when(claseClient.obtenerClasePorId(10L)).thenReturn(claseFake);

        List<InscripcionResponseDto> lista = inscripcionService.obtenerTodas();

        assertNotNull(lista);
        assertEquals(1, lista.size());
    }

    

    @Test
    public void testCancelarInscripcion() {
        Long id = 1L;

    
        when(inscripcionRepository.existsById(id)).thenReturn(true);
        doNothing().when(inscripcionRepository).deleteById(id);

        inscripcionService.cancelarInscripcion(id);

        verify(inscripcionRepository, times(1)).deleteById(id);
    }
}