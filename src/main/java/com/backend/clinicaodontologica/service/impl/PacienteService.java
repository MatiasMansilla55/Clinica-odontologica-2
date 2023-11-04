package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.repository.IDao;
import com.backend.clinicaodontologica.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente registrarPaciente(Paciente paciente){
        return pacienteIDao.registrar(paciente);
    }

    public List<Paciente> listarPacientes(){
        return pacienteIDao.listarTdos();
    }

    @Override
    public Paciente buscarPacientePorId(int id) {
        return pacienteIDao.buscarPorId(id);
    }

    @Override
    public void eliminarPaciente(int id) {
        pacienteIDao.eliminar(id);
    }
}
