package com.hospital.consultorio;

import com.hospital.consultorio.model.Doctor;
import com.hospital.consultorio.model.Consultorio;
import com.hospital.consultorio.repository.DoctorRepository;
import com.hospital.consultorio.repository.ConsultorioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsultorioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultorioApplication.class, args);
	}

	// Verificar si hay doctores y consultorios en la base de datos al iniciar la aplicación
	@Bean
	public CommandLineRunner run(DoctorRepository doctorRepository, ConsultorioRepository consultorioRepository) {
		return (args) -> {
			// Verifica si no hay doctores en la base de datos
			if (doctorRepository.count() == 0) {
				System.out.println("No hay doctores en la base de datos.");
				// Agregar doctores si no existen
				doctorRepository.save(new Doctor("Juan", "Pérez", "Gómez", "Cardiología"));
				doctorRepository.save(new Doctor("Ana", "Sánchez", "López", "Pediatría"));
				doctorRepository.save(new Doctor("Luis", "Martínez", "Ramírez", "Dermatología"));
			} else {
				System.out.println("Cargando doctores desde la base de datos...");
				doctorRepository.findAll().forEach(doctor -> {
					System.out.println(doctor.getNombre() + " " + doctor.getApellidoPaterno());
				});
			}

			// Verifica si no hay consultorios en la base de datos
			if (consultorioRepository.count() == 0) {
				System.out.println("No hay consultorios en la base de datos.");
				// Agregar consultorios si no existen
				consultorioRepository.save(new Consultorio(1, 1));
				consultorioRepository.save(new Consultorio(2, 1));
				consultorioRepository.save(new Consultorio(3, 2));
			} else {
				System.out.println("Cargando consultorios desde la base de datos...");
				consultorioRepository.findAll().forEach(consultorio -> {
					System.out.println("Consultorio " + consultorio.getNumero() + ", Piso: " + consultorio.getPiso());
				});
			}
		};
	}
}
