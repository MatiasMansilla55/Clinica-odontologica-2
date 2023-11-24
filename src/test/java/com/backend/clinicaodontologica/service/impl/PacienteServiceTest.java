package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;

import com.backend.clinicaodontologica.entity.Paciente;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    public PacienteServiceTest(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    @Test
    void deberiaRegistrarUnPaciente() {

        PacienteEntradaDto paciente = new PacienteEntradaDto("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02), new DomicilioEntradaDto("Calle", 123L, "Localidad", "Provincia"));

        PacienteSalidaDto pacienteRegistrado = pacienteService.registrarPaciente(paciente);

        Assertions.assertTrue(pacienteRegistrado.getId() != 0);

    }

    @Test
    void deberiaRetornarUnaListaNoVacia() {
        // Registra un paciente específico para este test
        PacienteEntradaDto paciente = new PacienteEntradaDto("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02), new DomicilioEntradaDto("Calle", 123L, "Localidad", "Provincia"));
        pacienteService.registrarPaciente(paciente);

        // Lista los pacientes y verifica que la lista no esté vacía
        List<PacienteSalidaDto> pacientes = pacienteService.listarPacientes();
        assertFalse(pacientes.isEmpty());

    }

    @Test
    void deberiaRetornarUnPacientePorId() {
        // Registra un paciente específico para este test
        PacienteEntradaDto paciente = new PacienteEntradaDto("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02), new DomicilioEntradaDto("Calle", 123L, "Localidad", "Provincia"));
        PacienteSalidaDto pacienteSalidaDto=pacienteService.registrarPaciente(paciente);

        // Lista los pacientes y verifica que la lista no esté vacía
        PacienteSalidaDto pacienteSalidaDto2 = pacienteService.buscarPacientePorId(pacienteSalidaDto.getId());
        assertSame(pacienteSalidaDto.getId(), pacienteSalidaDto2.getId());
    }

}