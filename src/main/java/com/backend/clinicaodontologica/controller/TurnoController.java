package com.backend.clinicaodontologica.controller;


import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.TurnosModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import com.backend.clinicaodontologica.service.impl.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/registrar")
    
    public ResponseEntity<TurnoSalidaDto> guardar(@RequestBody TurnoEntradaDto turno) throws BadRequestException {


        return new ResponseEntity<>(turnoService.registrarTurno(turno), HttpStatus.OK);


    }
    @GetMapping("/listar")
    public ResponseEntity<List<TurnoSalidaDto>> listarOdontologos(){
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminarOdontologo(@PathVariable Long id){
        turnoService.eliminarTurno(id);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<TurnoSalidaDto> actualizarOdontologo(@RequestBody TurnosModificacionEntradaDto turno){

        return new ResponseEntity<>(turnoService.actualizarTurno(turno),HttpStatus.OK);
    }
    @GetMapping("/buscarId/{id}")
    public TurnoSalidaDto buscarPorId(@PathVariable Long id){
        return  turnoService.buscarTurnoPorId(id);


    }
}
