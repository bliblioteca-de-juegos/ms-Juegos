package com.biblioteca.Ms_Juegos.hateoas;

import com.biblioteca.Ms_Juegos.Dto.JuegosResponseDTO;
import com.biblioteca.Ms_Juegos.Service.JuegosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/hateoas/juegos")
@Tag(name = "Juegos HATEOAS", description = "Consulta de juegos con enlaces de navegacion")
@RequiredArgsConstructor
public class JuegosHateoasController {

    private final JuegosService juegosService;
    private final JuegosModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Listar juegos con enlaces HATEOAS")
    public CollectionModel<EntityModel<JuegosResponseDTO>> obtenerTodos() {
        List<EntityModel<JuegosResponseDTO>> juegos = juegosService.obtenerTodas().stream()
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of(
                juegos,
                linkTo(methodOn(JuegosHateoasController.class).obtenerTodos()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un juego con enlaces HATEOAS")
    public ResponseEntity<EntityModel<JuegosResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return juegosService.obtenerPorId(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoriaId}")
    @Operation(summary = "Listar juegos de una categoria con enlaces HATEOAS")
    public CollectionModel<EntityModel<JuegosResponseDTO>> obtenerPorCategoria(@PathVariable Long categoriaId) {
        List<EntityModel<JuegosResponseDTO>> juegos = juegosService.obtenerPorCategoria(categoriaId).stream()
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of(
                juegos,
                linkTo(methodOn(JuegosHateoasController.class).obtenerPorCategoria(categoriaId)).withSelfRel(),
                linkTo(methodOn(JuegosHateoasController.class).obtenerTodos()).withRel("juegos")
        );
    }
}
