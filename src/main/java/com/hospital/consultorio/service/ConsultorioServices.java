package com.hospital.consultorio.service;

import com.hospital.consultorio.model.Consultorio;
import com.hospital.consultorio.repository.ConsultorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultorioServices {

    @Autowired
    private ConsultorioRepository consultorioRepository;

    // Obtener todos los consultorios
    public List<Consultorio> obtenerTodosConsultorios() {
        return consultorioRepository.findAll();
    }

    // Guardar un nuevo consultorio
    public void guardarConsultorio(Consultorio consultorio) {
        consultorioRepository.save(consultorio);  // Guardar el consultorio en la base de datos
    }
}
