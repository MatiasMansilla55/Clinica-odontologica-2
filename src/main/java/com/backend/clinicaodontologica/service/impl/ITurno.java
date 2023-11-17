package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.TurnosModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.model.Turno;

import java.util.List;

public interface ITurno {
    TurnoSalidaDto registrarPaciente(TurnoEntradaDto turno);

    List<TurnoSalidaDto> listarPacientes();

    TurnoSalidaDto buscarPacientePorId(Long id);

    TurnoSalidaDto actualizarPaciente(TurnosModificacionEntradaDto paciente);

    void eliminarPaciente(Long id);

    TurnoSalidaDto buscarPacientePorDni(int dni);
}
