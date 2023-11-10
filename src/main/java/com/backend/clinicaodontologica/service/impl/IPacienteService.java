package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.model.Paciente;

import java.util.List;

public interface IPacienteService {
    Paciente registrarPaciente(PacienteEntradaDto paciente);

    List<Paciente> listarPacientes();
    Paciente buscarPacientePorId(int id);

    void eliminarPaciente(int id);
    Paciente actualizarPaciente(Paciente paciente);
}
