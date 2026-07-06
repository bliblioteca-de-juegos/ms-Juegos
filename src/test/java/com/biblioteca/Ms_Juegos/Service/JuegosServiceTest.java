package com.biblioteca.Ms_Juegos.Service;

import com.biblioteca.Ms_Juegos.Dto.JuegosResponseDTO;
import com.biblioteca.Ms_Juegos.Modelo.Juegos;
import com.biblioteca.Ms_Juegos.Repository.JuegosRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JuegosServiceTest {

    @Mock
    private JuegosRepository juegosRepository;
    @Mock
    private WebClient categoriaWebClient;
    @Mock
    private WebClient clasificacionWebClient;

    private JuegosService juegosService;
    private final Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        juegosService = new JuegosService();
        ReflectionTestUtils.setField(juegosService, "juegosRepository", juegosRepository);
        ReflectionTestUtils.setField(juegosService, "categoriaWebClient", categoriaWebClient);
        ReflectionTestUtils.setField(juegosService, "clasificacionWebClient", clasificacionWebClient);
    }

    @Test
    void obtenerTodasRetornaLosJuegosRegistrados() {
        String titulo = faker.esports().game();
        Juegos juego = new Juegos(1L, titulo, faker.lorem().sentence(), 19990.0, 2L, 3L);
        when(juegosRepository.findAll()).thenReturn(List.of(juego));

        List<JuegosResponseDTO> resultado = juegosService.obtenerTodas();

        assertEquals(1, resultado.size());
        assertEquals(titulo, resultado.getFirst().getTitulo());
        assertEquals(2L, resultado.getFirst().getCategoriaId());
        assertEquals(3L, resultado.getFirst().getClasificacionId());
    }

    @Test
    void obtenerPorIdRetornaVacioCuandoElJuegoNoExiste() {
        Long id = faker.number().numberBetween(1L, 1000L);
        when(juegosRepository.findById(id)).thenReturn(Optional.empty());

        assertFalse(juegosService.obtenerPorId(id).isPresent());
    }
}
