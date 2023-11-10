package com.backend.clinicaodontologica.service.impl;
import com.backend.clinicaodontologica.model.Odontologo;
import com.backend.clinicaodontologica.model.Paciente;
import java.util.List;

public interface IOdontologoService {

        Odontologo registrarOdontologo(Odontologo odontologo);

        List<Odontologo> listarOdontologo();
        Odontologo buscarOdontologoPorId(int id);

        void eliminarOdontologo(int id);
    }

