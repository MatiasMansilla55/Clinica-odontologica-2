package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;

import com.backend.clinicaodontologica.service.impl.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    public IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    @GetMapping("/buscarIdDeOdontologo")
    public String buscarOdontologoPorId(Model model, @RequestParam Long id){
        OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(id);

        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());

        return "odontologo";
    }

    @GetMapping("/listar")
    public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologos(){
        return new ResponseEntity<>(odontologoService.listarOdontologo(), HttpStatus.OK);
    }
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoSalidaDto> guardar(@RequestBody OdontologoEntradaDto odontologo){
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologo),HttpStatus.OK);
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminarOdontologo(@PathVariable Long id){
        odontologoService.eliminarOdontologo(id);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoSalidaDto> actualizarOdontologo(@RequestBody OdontologoModificacionEntradaDto odontologo){
        return new ResponseEntity<>(odontologoService.actualizarOdontologo(odontologo),HttpStatus.OK);
    }
}
