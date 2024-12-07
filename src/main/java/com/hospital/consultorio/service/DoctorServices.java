package com.hospital.consultorio.service;

import com.hospital.consultorio.model.Doctor;
import com.hospital.consultorio.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServices {

    @Autowired
    private DoctorRepository doctorRepository;

    // Obtener todos los doctores
    public List<Doctor> obtenerTodosDoctores() {
        return doctorRepository.findAll();  // Retorna todos los doctores desde la base de datos
    }

    // Guardar un nuevo doctor
    public void guardarDoctor(Doctor doctor) {
        doctorRepository.save(doctor);  // Guarda el nuevo doctor en la base de datos
    }
}
