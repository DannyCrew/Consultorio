package com.hospital.consultorio.controller;

import com.hospital.consultorio.model.Consultorio;
import com.hospital.consultorio.service.ConsultorioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/consultorios")
public class ConsultorioController {

    @Autowired
    private ConsultorioServices consultorioServices;

    // Ver la lista de todos los consultorios
    @GetMapping("/listaConsultorios")
    public String listaConsultorios(Model model) {
        List<Consultorio> consultorios = consultorioServices.obtenerTodosConsultorios();  // Obtener consultorios desde la base de datos
        model.addAttribute("consultorios", consultorios);
        return "consultorios/listaConsultorios";  // Vista que muestra la lista de consultorios
    }

    // Página para agregar un nuevo consultorio
    @GetMapping("/agregar")
    public String nuevoConsultorioForm(Model model) {
        model.addAttribute("consultorio", new Consultorio());  // Crear un objeto Consultorio vacío para el formulario
        return "consultorios/agregarConsultorio";  // Vista para agregar un nuevo consultorio
    }

    // Guardar el nuevo consultorio
    @PostMapping("/guardar")
    public String guardarConsultorio(@ModelAttribute Consultorio consultorio) {
        consultorioServices.guardarConsultorio(consultorio);  // Guardar el consultorio usando el servicio
        return "redirect:/consultorios/listaConsultorios";  // Redirigir a la lista de consultorios
    }
}
