package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;

import com.backend.clinicaodontologica.repository.IDao;
import com.backend.clinicaodontologica.model.Odontologo;
import com.backend.clinicaodontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologService.class);
    private IDao<Odontologo> odontologoIDao;
    private ModelMapper modelMapper;


    public OdontologService(IDao<Odontologo> odontologoIDao, ModelMapper modelMapper) {
        this.odontologoIDao = odontologoIDao;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {
        LOGGER.info("PacienteEntradaDto: " + JsonPrinter.toString(odontologo));
        Odontologo OdontologoEntidad = modelMapper.map(odontologo, Odontologo.class);

        //mandamos a persistir a la capa dao y obtenemos una entidad
        Odontologo odontologoAPersistir = odontologoIDao.registrar(OdontologoEntidad);
        //transformamos la entidad obtenida en salidaDto
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoAPersistir, OdontologoSalidaDto.class);
        LOGGER.info("PacienteSalidaDto: " + JsonPrinter.toString(odontologoSalidaDto));
        return odontologoSalidaDto;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologo() {
        List<OdontologoSalidaDto> odontologoSalidaDtos = odontologoIDao.listarTdos()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
                .toList();
        LOGGER.info("Listado de todos los pacientes: {}", JsonPrinter.toString(odontologoSalidaDtos));
        return odontologoSalidaDtos;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(int id) {
        Odontologo odontologoBuscado = odontologoIDao.buscarPorId(id);
        OdontologoSalidaDto odontologoEncontrado = null;

        if(odontologoBuscado != null){
            odontologoEncontrado =  modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(odontologoEncontrado));
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return odontologoEncontrado;
    }

    @Override
    public void eliminarOdontologo(int id) {
        odontologoIDao.eliminar(id);
    }

    @Override
    public OdontologoSalidaDto actualizarOdontologo(OdontologoEntradaDto odontologo) {
        LOGGER.info("OdontologoEntradaDto: " + JsonPrinter.toString(odontologo));
        Odontologo odontologoEntidad = modelMapper.map(odontologo, Odontologo.class);

        //mandamos a persistir a la capa dao y obtenemos una entidad
        Odontologo odontologoAPersistir = odontologoIDao.actualizar(odontologoEntidad);
        //transformamos la entidad obtenida en salidaDto
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoAPersistir , OdontologoSalidaDto.class);
        LOGGER.info("OdontologoSalidaDto: " + JsonPrinter.toString(odontologoSalidaDto));
        return odontologoSalidaDto;
    }

    private void configureMapping(){
        modelMapper.typeMap(OdontologoEntradaDto.class, Odontologo.class);

        modelMapper.typeMap(Odontologo.class, OdontologoSalidaDto.class);


    }
}
