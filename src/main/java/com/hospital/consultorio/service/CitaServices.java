package com.hospital.consultorio.service;

import com.hospital.consultorio.model.Cita;
import com.hospital.consultorio.model.Doctor;
import com.hospital.consultorio.model.Consultorio;
import com.hospital.consultorio.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaServices {

    @Autowired
    private CitaRepository citaRepository;

    // Verificar que no haya más de 8 citas para el mismo doctor en un día
    private boolean verificarMaximoCitasPorDoctor(Doctor doctor, LocalDateTime horarioConsulta) {
        LocalDateTime inicioDelDia = horarioConsulta.toLocalDate().atStartOfDay();
        LocalDateTime finDelDia = horarioConsulta.toLocalDate().atTime(23, 59, 59);
        long citasDelDoctor = citaRepository.findByDoctorIdAndHorarioConsultaBetween(doctor.getId(), inicioDelDia, finDelDia).size();
        return citasDelDoctor >= 8;
    }

    // Verificar que no haya citas en el mismo consultorio o con el mismo doctor en la misma hora
    private boolean verificarCitaExistente(Consultorio consultorio, Doctor doctor, LocalDateTime horarioConsulta) {
        // Verificar si ya existe una cita para el consultorio a esa hora
        List<Cita> citasEnConsultorio = citaRepository.findByConsultorioIdAndHorarioConsultaBetween(consultorio.getId(), horarioConsulta, horarioConsulta.plusMinutes(59));
        if (!citasEnConsultorio.isEmpty()) {
            return true;
        }

        // Verificar si ya existe una cita para el doctor a esa hora
        List<Cita> citasDeDoctor = citaRepository.findByDoctorIdAndHorarioConsultaBetween(doctor.getId(), horarioConsulta, horarioConsulta.plusMinutes(59));
        return !citasDeDoctor.isEmpty();
    }

    // Verificar que el paciente no tenga citas con menos de 2 horas de diferencia
    private boolean verificarCitasPaciente(String nombrePaciente, LocalDateTime horarioConsulta) {
        LocalDateTime inicioDelDia = horarioConsulta.toLocalDate().atStartOfDay();
        LocalDateTime finDelDia = horarioConsulta.toLocalDate().atTime(23, 59, 59);
        List<Cita> citasPaciente = citaRepository.findByNombrePacienteAndHorarioConsultaBetween(nombrePaciente, inicioDelDia, finDelDia);
        for (Cita cita : citasPaciente) {
            if (Math.abs(cita.getHorarioConsulta().getHour() - horarioConsulta.getHour()) < 2) {
                return true;
            }
        }
        return false;
    }

    // Método para agendar una cita con todas las validaciones
    public Cita agendarCita(Cita cita) {
        // Verificar si el doctor tiene más de 8 citas en el día
        if (verificarMaximoCitasPorDoctor(cita.getDoctor(), cita.getHorarioConsulta())) {
            throw new IllegalArgumentException("El doctor ya tiene el máximo de citas para este día.");
        }

        // Verificar si hay citas en el mismo consultorio o para el mismo doctor a la misma hora
        if (verificarCitaExistente(cita.getConsultorio(), cita.getDoctor(), cita.getHorarioConsulta())) {
            throw new IllegalArgumentException("Ya existe una cita para el doctor o el consultorio en esa hora.");
        }

        // Verificar si el paciente tiene citas a menos de 2 horas de diferencia
        if (verificarCitasPaciente(cita.getNombrePaciente(), cita.getHorarioConsulta())) {
            throw new IllegalArgumentException("El paciente ya tiene una cita en menos de 2 horas.");
        }

        // Si pasa todas las validaciones, guarda la cita
        return citaRepository.save(cita);
    }

    public List<Cita> obtenerCitas() {
        return citaRepository.findAll();
    }

    public List<Cita> obtenerCitasPorDoctor(Long doctorId) {
        return citaRepository.findByDoctorId(doctorId);
    }

    public List<Cita> obtenerCitasPorConsultorio(Long consultorioId) {
        return citaRepository.findByConsultorioId(consultorioId);
    }
}
