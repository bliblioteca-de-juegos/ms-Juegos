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
    private String titulo;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false, length = 100)
    private Double precio;


    @Column(name = "categoria_id", nullable = false)
    private Long categoriaId;

    @Column(name = "clasificacion_id", nullable = false)
    private Long clasificacionId;

}
