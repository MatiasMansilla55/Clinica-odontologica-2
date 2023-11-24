package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class OdontologServiceTest {
    @Autowired
    private OdontologService odontologService;

    @Autowired
    public OdontologServiceTest(OdontologService odontologService) {
        this.odontologService = odontologService;
    }

    @Test
    void deberiaRegistrarYRetonarUnOdontologo(){
        OdontologoEntradaDto odontologoEntradaDtoDto = new OdontologoEntradaDto(123,"matias","perez");


        OdontologoSalidaDto odontologoRegistrado= odontologService.registrarOdontologo(odontologoEntradaDtoDto);

        Assertions.assertTrue(odontologoRegistrado.getId() != 0);
    }

    @Test
    void deberiaRetornarUnOdontologoPorId() {
        // Registra un paciente específico para este test
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(123456,"Nombre", "Apellido");
        OdontologoSalidaDto odontologoSalidaDto=odontologService.registrarOdontologo(odontologoEntradaDto);

        // Lista los pacientes y verifica que la lista no esté vacía
        OdontologoSalidaDto odontologoSalidaDto2 = odontologService.buscarOdontologoPorId(odontologoSalidaDto.getId());
        assertSame(odontologoSalidaDto.getId() , odontologoSalidaDto2.getId());
    }

    @Test
void deberiaRetornarLaListaDeOdonotologos(){

    OdontologoEntradaDto odontologoGuardado= new OdontologoEntradaDto(3,"sebastian","sanchez");
    OdontologoEntradaDto odontologoGuardado2= new OdontologoEntradaDto(44,"Lucia","Marquez");

    odontologService.registrarOdontologo(odontologoGuardado);
    odontologService.registrarOdontologo(odontologoGuardado2);
    //LOGGER.info(odontologServiceMemoria.listaDeOdontologos());
    Assertions.assertFalse(odontologService.listarOdontologo().isEmpty());
    }
}