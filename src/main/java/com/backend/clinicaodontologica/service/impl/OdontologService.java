package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.model.Paciente;
import com.backend.clinicaodontologica.repository.IDao;
import com.backend.clinicaodontologica.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologService implements IOdontologoService {


    private IDao<Odontologo> odontologoIDao;

    public OdontologService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologoIDao.registrar(odontologo);
    }

    @Override
    public List<Odontologo> listarOdontologo() {
        return odontologoIDao.listarTdos();
    }

    @Override
    public Odontologo buscarOdontologoPorId(int id) {
        return odontologoIDao.buscarPorId(id);
    }

    @Override
    public void eliminarOdontologo(int id) {
        odontologoIDao.eliminar(id);
    }
}
