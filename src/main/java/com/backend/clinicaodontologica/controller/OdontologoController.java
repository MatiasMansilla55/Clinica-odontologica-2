package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.model.Odontologo;

import com.backend.clinicaodontologica.service.impl.IOdontologoService;
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
    public String buscarOdontologoPorId(Model model, @RequestParam int id){
        Odontologo odontologo = odontologoService.buscarOdontologoPorId(id);

        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());

        return "odontologo";
    }

    @GetMapping("/listar")
    public List<Odontologo> listarOdontologos(){
        return odontologoService.listarOdontologo();
    }
    @PostMapping("/registrar")
    public Odontologo guardar(@RequestBody Odontologo odontologo){
        return odontologoService.registrarOdontologo(odontologo);
    }
    @DeleteMapping("/eliminar")
    public void eliminarOdontologo(@RequestParam int id){
        odontologoService.eliminarOdontologo(id);
    }
}
