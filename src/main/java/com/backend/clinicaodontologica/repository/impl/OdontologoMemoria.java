package com.backend.clinicaodontologica.repository.impl;


import com.backend.clinicaodontologica.repository.IDao;
import com.backend.clinicaodontologica.model.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OdontologoMemoria implements IDao<Odontologo> {
    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoMemoria.class);

    private List<Odontologo> odontologoList = new ArrayList<>();

    public OdontologoMemoria(List<Odontologo> odontologoList) {
        this.odontologoList = odontologoList;
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        int id = odontologoList.size() + 1;

        Odontologo odontologoGuardado = new Odontologo(id, odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());
        odontologoList.add(odontologoGuardado);

        LOGGER.info("Odontologo guardado:" + odontologoGuardado);


        return odontologoGuardado;
    }

    @Override
    public List<Odontologo> listarTdos() {
        return odontologoList;
    }

    @Override
    public Odontologo buscarPorId(int id) {
        return null;
    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        return null;
    }
}