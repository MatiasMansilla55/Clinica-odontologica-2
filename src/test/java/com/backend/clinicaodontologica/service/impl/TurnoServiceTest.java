package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.entity.Domicilio;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class TurnoServiceTest {
    private final Logger LOGGER = LoggerFactory.getLogger(TurnoServiceTest.class);
private final TurnoService turnoService;
private final PacienteService pacienteService;
private final OdontologService odontologService;

    @Autowired
    public TurnoServiceTest(TurnoService turnoService, PacienteService pacienteService, OdontologService odontologService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologService = odontologService;
    }

    @Test
    void deberiaDeRegistrarUnTurno() throws BadRequestException {
        PacienteEntradaDto pacienteEntradaDto=new PacienteEntradaDto("Nombre","Apellido", 123456, LocalDate.of(2023, 05, 02), new DomicilioEntradaDto("Calle", 123L, "Localidad", "Provincia"));
        OdontologoEntradaDto odontologoEntradaDto= new OdontologoEntradaDto(123,"matias","perez");
        PacienteSalidaDto pacienteSalidaDto=pacienteService.registrarPaciente(pacienteEntradaDto);
        OdontologoSalidaDto odontologoSalidaDto=odontologService.registrarOdontologo(odontologoEntradaDto);

        TurnoEntradaDto turnoEntradaDto=new TurnoEntradaDto(LocalDateTime.of(2023,11,23,17,56,50),odontologoSalidaDto,pacienteSalidaDto);
        TurnoSalidaDto turnoSalidaDto= turnoService.registrarTurno(turnoEntradaDto);


        assertNotNull(turnoSalidaDto.getId());

    }
    @Test
    void deberiaDevolverUnaListaDeTurnos() throws BadRequestException {
        PacienteEntradaDto pacienteEntradaDto=new PacienteEntradaDto("Nombre","Apellido", 123456, LocalDate.of(2023, 05, 02), new DomicilioEntradaDto("Calle", 123L, "Localidad", "Provincia"));
        OdontologoEntradaDto odontologoEntradaDto= new OdontologoEntradaDto(123,"matias","perez");
        PacienteSalidaDto pacienteSalidaDto=pacienteService.registrarPaciente(pacienteEntradaDto);
        OdontologoSalidaDto odontologoSalidaDto=odontologService.registrarOdontologo(odontologoEntradaDto);

        TurnoEntradaDto turnoEntradaDto=new TurnoEntradaDto(LocalDateTime.of(2023,11,23,17,56,50),odontologoSalidaDto,pacienteSalidaDto);
        TurnoSalidaDto turnoSalidaDto= turnoService.registrarTurno(turnoEntradaDto);
        LOGGER.info("el turno registrado es:" + turnoSalidaDto);
        List<TurnoSalidaDto> turnoSalidaDtos=turnoService.listarTurnos();
        assertFalse(turnoSalidaDtos.isEmpty());
    }
    @Test
    void deberiaBuscarUnTurnoPorId() throws BadRequestException {
        PacienteEntradaDto pacienteEntradaDto=new PacienteEntradaDto("Nombre","Apellido", 123456, LocalDate.of(2023, 05, 02), new DomicilioEntradaDto("Calle", 123L, "Localidad", "Provincia"));
        OdontologoEntradaDto odontologoEntradaDto= new OdontologoEntradaDto(123,"matias","perez");
        PacienteSalidaDto pacienteSalidaDto=pacienteService.registrarPaciente(pacienteEntradaDto);
        OdontologoSalidaDto odontologoSalidaDto=odontologService.registrarOdontologo(odontologoEntradaDto);

        TurnoEntradaDto turnoEntradaDto=new TurnoEntradaDto(LocalDateTime.of(2023,11,23,17,56,50),odontologoSalidaDto,pacienteSalidaDto);
        TurnoSalidaDto turnoSalidaDto= turnoService.registrarTurno(turnoEntradaDto);
        LOGGER.info("el turno registrado es:" + turnoSalidaDto);
        TurnoSalidaDto turnoSalidaDto2= turnoService.buscarTurnoPorId(turnoSalidaDto.getId());
        assertSame(turnoSalidaDto2.getId(), turnoSalidaDto.getId());
    }
}