package com.biblioteca.Ms_Juegos.Modelo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Juegos")
public class Juegos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String Titulo;

    @Column(nullable = false, length = 100)
    private String descripcion;

    @Column(nullable = false, length = 100)
    private Double precio;

    // Solo el ID de la especialidad. NO hay FK en la BD.
    // ms-mascotas valida por HTTP que este ID exista en ms-especialidades.
    @Column(name = "categoria_id", nullable = false)
    private Long categoriaId;

}
