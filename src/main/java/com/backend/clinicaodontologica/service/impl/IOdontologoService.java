package com.backend.clinicaodontologica.service.impl;
import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.model.Odontologo;
import com.backend.clinicaodontologica.model.Paciente;
import java.util.List;

public interface IOdontologoService {

        OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo);

        List<OdontologoSalidaDto> listarOdontologo();
        OdontologoSalidaDto buscarOdontologoPorId(int id);

        void eliminarOdontologo(int id);
        OdontologoSalidaDto actualizarOdontologo(OdontologoEntradaDto odontologo);
    }

