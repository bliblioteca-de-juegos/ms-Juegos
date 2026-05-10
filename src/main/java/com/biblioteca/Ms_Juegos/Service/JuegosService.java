package com.biblioteca.Ms_Juegos.Service;


import com.biblioteca.Ms_Juegos.Dto.JuegosRequestDTO;
import com.biblioteca.Ms_Juegos.Dto.JuegosResponseDTO;
import com.biblioteca.Ms_Juegos.Modelo.Juegos;
import com.biblioteca.Ms_Juegos.Repository.JuegosRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class JuegosService {
    private final JuegosRepository juegosRepository;

    private final WebClient webClient;

    private JuegosResponseDTO mapToDTO(Juegos j) {
        return new JuegosResponseDTO(
                j.getId(), j.getTitulo(), j.getDescripcion(), j.getPrecio(), j.getCategoriaId()
        );
    }

    private void validarCategoria(Long categoriaId) {
        try {
            webClient.get()
                    .uri("/api/categorias/{id}", categoriaId)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            log.info(">>>> Categoria {} validada correctamente (WebClient)", categoriaId);
        } catch (WebClientResponseException.NotFound e) {
            throw new RuntimeException("La categoria con id " + categoriaId + "no existe en Ms_Categoria.");
        } catch (Exception e) {
            throw new RuntimeException("No se puede conectar con Ms_Categoria:" + e.getMessage());

        }
    }

    public List<JuegosResponseDTO> obtenerTodas() {
        return juegosRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<JuegosResponseDTO> obtenerPorId(Long id) {
        return juegosRepository.findById(id).map(this::mapToDTO);
    }

    public List<JuegosResponseDTO> obtenerPorCategoria(Long cateId) {
        return juegosRepository.findByCategoriaId(cateId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<JuegosResponseDTO> buscarPorTitulo(String titulo) {
        return juegosRepository.buscarPorTitulo(titulo).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<JuegosResponseDTO> buscarPorPrecio(int min, int max) {
        return juegosRepository.findByPrecioBetween(min, max).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public JuegosResponseDTO Guardar(@Valid  JuegosResponseDTO dto) {
        validarCategoria(dto.getCategoriaId());
        Juegos j = new Juegos(null, dto.getTitulo(), dto.getDescripcion(), dto.getPrecio(), dto.getCategoriaId());
        return mapToDTO(juegosRepository.save(j));
    }

    public Optional<JuegosResponseDTO> actualizar(Long id, JuegosResponseDTO dto) {
        return juegosRepository.findById(id).map(existente -> {
            validarCategoria(dto.getCategoriaId());
            existente.setTitulo(dto.getTitulo());
            existente.setDescripcion(dto.getDescripcion());
            existente.setPrecio(dto.getPrecio());
            existente.setCategoriaId(dto.getCategoriaId());
            return mapToDTO(juegosRepository.save(existente));
        });
    }

    public void eliminar(Long id) {juegosRepository.deleteById(id);}



}
