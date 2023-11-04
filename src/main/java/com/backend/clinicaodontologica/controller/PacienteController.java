package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.model.Paciente;

import com.backend.clinicaodontologica.service.impl.IPacienteService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public Paciente guardar(@RequestBody Paciente paciente){
       return pacienteService.registrarPaciente(paciente);
    }
    @DeleteMapping("/eliminar")
    public void eliminarPaciente(@RequestParam int id){
        pacienteService.eliminarPaciente(id);
    }

}