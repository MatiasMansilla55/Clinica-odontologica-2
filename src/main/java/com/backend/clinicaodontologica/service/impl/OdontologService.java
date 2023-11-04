package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.repository.IDao;
import com.backend.clinicaodontologica.model.Odontologo;

import java.util.List;

public class OdontologService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo registrarOdontolog(Odontologo odontologo){
        return odontologoIDao.registrar(odontologo);
    }

    public List<Odontologo> listaDeOdontologos(){
        return odontologoIDao.listarTdos();
    }
}
