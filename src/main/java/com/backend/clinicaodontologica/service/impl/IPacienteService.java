package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.model.Paciente;

import java.util.List;

public interface IPacienteService {
    Paciente registrarPaciente(Paciente paciente);

    List<Paciente> listarPacientes();
    Paciente buscarPacientePorId(int id);

    void eliminarPaciente(int id);
}
