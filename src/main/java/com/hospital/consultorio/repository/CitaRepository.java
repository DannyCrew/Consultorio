package com.hospital.consultorio.repository;

import com.hospital.consultorio.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByDoctorId(Long doctorId);

    List<Cita> findByConsultorioId(Long consultorioId);

    List<Cita> findByNombrePacienteAndHorarioConsultaBetween(String nombrePaciente, LocalDateTime start, LocalDateTime end);

    List<Cita> findByDoctorIdAndHorarioConsultaBetween(Long doctorId, LocalDateTime start, LocalDateTime end);

    List<Cita> findByConsultorioIdAndHorarioConsultaBetween(Long consultorioId, LocalDateTime start, LocalDateTime end);
}
