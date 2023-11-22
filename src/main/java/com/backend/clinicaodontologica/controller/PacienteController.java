package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.PacienteModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;

import com.backend.clinicaodontologica.service.impl.IPacienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    @GetMapping("/buscarId")
    public String buscarPacientePorId(Model model, @RequestParam Long id){
        PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(id);

        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());

        return "paciente";

    }

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteSalidaDto>> listarPacientes(){
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }
    @PostMapping("/registrar")
    public ResponseEntity<PacienteSalidaDto> guardar(@RequestBody @Valid PacienteEntradaDto paciente){
       return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
    }
    @DeleteMapping("/eliminar/{id}")
    public  ResponseEntity<Void> eliminarPaciente(@PathVariable Long id){
        pacienteService.eliminarPaciente(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }



    //buscar paciente con PathVariable
    @GetMapping("{id}")
    public ResponseEntity<PacienteSalidaDto> obtenerPacientePorId(@PathVariable Long id){
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<PacienteSalidaDto> actualizarPaciente(@RequestBody PacienteModificacionEntradaDto paciente){
        return new ResponseEntity<>(pacienteService.actualizarPaciente(paciente),HttpStatus.OK);
    }

}