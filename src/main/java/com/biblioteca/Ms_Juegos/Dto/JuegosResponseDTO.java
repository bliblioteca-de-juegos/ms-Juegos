package com.biblioteca.Ms_Juegos.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JuegosResponseDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private Double precio;
    private Long categoriaId;
}
