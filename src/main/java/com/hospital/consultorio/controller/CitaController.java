package com.hospital.consultorio.controller;

import com.hospital.consultorio.model.Cita;
import com.hospital.consultorio.service.CitaServices;
import com.hospital.consultorio.service.DoctorServices;
import com.hospital.consultorio.service.ConsultorioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaServices citaServices;

    @Autowired
    private DoctorServices doctorServices;

    @Autowired
    private ConsultorioServices consultorioServices;

    // Página principal para ver citas
    @GetMapping
    public String verCitas(Model model) {
        List<Cita> citas = citaServices.obtenerCitas();  // Obtener todas las citas
        model.addAttribute("citas", citas);
        return "citas/verCitas";  // Mostrar la vista de citas
    }

    // Página para agregar una nueva cita
    @GetMapping("/nueva")
    public String nuevaCita(Model model) {
        // Obtener los doctores y consultorios disponibles
        model.addAttribute("doctores", doctorServices.obtenerTodosDoctores());
        model.addAttribute("consultorios", consultorioServices.obtenerTodosConsultorios());
        return "citas/agregarCita";  // Mostrar el formulario para agendar cita
    }

    // Registrar una nueva cita
    @PostMapping("/agregar")
    public String agregarCita(@ModelAttribute Cita cita) {
        citaServices.agendarCita(cita);  // Agendar la cita
        return "redirect:/citas";  // Redirigir a la lista de citas
    }
}
