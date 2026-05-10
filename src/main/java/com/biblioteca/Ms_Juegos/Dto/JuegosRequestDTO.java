package com.biblioteca.Ms_Juegos.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JuegosRequestDTO {

    @NotBlank(message = "El titulo no puede estar vacio")
    private String titulo;

    @NotBlank(message = "La descripcion es obligatorio")
    private String descripcion;

    @NotNull(message = "el precio es obligarorio")
    @Positive(message = "El precio debe ser mayor que a 0")
    private Double precio;

    @NotNull(message = "La categoria es obligatoria")
    private Long categoriaId;

    @NotNull(message = "La clasificacion es obligatoria")
    private Long clasificacionId;

}
