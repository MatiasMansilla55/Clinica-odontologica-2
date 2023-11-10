package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.model.Paciente;

import com.backend.clinicaodontologica.service.impl.IPacienteService;

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
    public String buscarPacientePorId(Model model, @RequestParam int id){
        Paciente paciente = pacienteService.buscarPacientePorId(id);

        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());

        return "paciente";

    }

    @GetMapping("/listar")
    public List<Paciente> listarPacientes(){
        return pacienteService.listarPacientes();
    }
    @PostMapping("/registrar")
    public Paciente guardar(@RequestBody @Valid PacienteEntradaDto paciente){
       return pacienteService.registrarPaciente(paciente);
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminarPaciente(@PathVariable int id){
        pacienteService.eliminarPaciente(id);
    }



    //buscar paciente con PathVariable
    @GetMapping("/buscarPaciente/{id}")
    public Paciente obtenerPaciente(@PathVariable int id ){
        return pacienteService.buscarPacientePorId(id);
    }
    @PutMapping("/actualizar")
    public Paciente actualizarPaciente(@RequestBody Paciente paciente){
        return pacienteService.actualizarPaciente(paciente);
    }
    @PostMapping("/registrar")
    public ResponseEntity<PacienteSalidaDto> registrarPaciente(@RequestBody @Valid PacienteEntradaDto paciente){
        return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
    }

}