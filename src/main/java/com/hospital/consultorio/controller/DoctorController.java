package com.hospital.consultorio.controller;

import com.hospital.consultorio.model.Doctor;
import com.hospital.consultorio.service.DoctorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctores")
public class DoctorController {

    @Autowired
    private DoctorServices doctorServices;

    // Página para ver la lista de doctores
    @GetMapping("/listaDoctores")
    public String listaDoctores(Model model) {
        List<Doctor> doctores = doctorServices.obtenerTodosDoctores();  // Obtener doctores desde la base de datos
        model.addAttribute("doctores", doctores);
        return "doctores/listaDoctores";
    }

    // Página para agregar un nuevo doctor
    @GetMapping("/agregar")
    public String nuevoDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());  // Crear un objeto Doctor vacío para el formulario
        return "doctores/agregarDoctores";
    }

    // Guardar el nuevo doctor
    @PostMapping("/guardar")
    public String guardarDoctor(@ModelAttribute Doctor doctor) {
        doctorServices.guardarDoctor(doctor);
        return "redirect:/doctores/listaDoctores";
    }
}
