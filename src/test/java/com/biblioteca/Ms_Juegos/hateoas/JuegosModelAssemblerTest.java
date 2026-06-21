package com.biblioteca.Ms_Juegos.hateoas;

import com.biblioteca.Ms_Juegos.Dto.JuegosResponseDTO;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JuegosModelAssemblerTest {

    private final Faker faker = new Faker();

    @Test
    void agregaEnlacesAlJuegoCategoriaYClasificacion() {
        JuegosModelAssembler assembler = new JuegosModelAssembler(
                "http://localhost:8081",
                "http://localhost:8093"
        );
        JuegosResponseDTO juego = new JuegosResponseDTO(
                1L,
                faker.esports().game(),
                faker.lorem().sentence(),
                19990.0,
                2L,
                3L
        );

        EntityModel<JuegosResponseDTO> modelo = assembler.toModel(juego);

        assertTrue(modelo.hasLink("self"));
        assertTrue(modelo.hasLink("juegos"));
        assertEquals(
                "http://localhost:8081/api/v2/hateoas/categorias/2",
                modelo.getRequiredLink("categoria").getHref()
        );
        assertEquals(
                "http://localhost:8093/api/v2/hateoas/clasificaciones/3",
                modelo.getRequiredLink("clasificacion").getHref()
        );
    }
}
