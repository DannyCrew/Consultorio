package com.hospital.consultorio.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Consultorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;
    private int piso;

    // Constructor por defecto sin argumentos (necesario para JPA)
    public Consultorio() {
    }

    // Constructor adicional para inicializar el consultorio con número y piso
    public Consultorio(int numero, int piso) {
        this.numero = numero;
        this.piso = piso;
    }
}
