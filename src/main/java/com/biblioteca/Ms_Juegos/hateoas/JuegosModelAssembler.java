package com.biblioteca.Ms_Juegos.hateoas;

import com.biblioteca.Ms_Juegos.Dto.JuegosResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class JuegosModelAssembler
        implements RepresentationModelAssembler<JuegosResponseDTO, EntityModel<JuegosResponseDTO>> {

    private final String categoriaUrl;
    private final String clasificacionUrl;

    public JuegosModelAssembler(
            @Value("${ms.categoria.url}") String categoriaUrl,
            @Value("${ms.clasificacion.url}") String clasificacionUrl) {
        this.categoriaUrl = categoriaUrl;
        this.clasificacionUrl = clasificacionUrl;
    }

    @Override
    public EntityModel<JuegosResponseDTO> toModel(JuegosResponseDTO juego) {
        return EntityModel.of(
                juego,
                linkTo(methodOn(JuegosHateoasController.class).obtenerPorId(juego.getId())).withSelfRel(),
                linkTo(methodOn(JuegosHateoasController.class).obtenerTodos()).withRel("juegos"),
                Link.of(categoriaUrl + "/api/v2/hateoas/categorias/" + juego.getCategoriaId(), "categoria"),
                Link.of(clasificacionUrl + "/api/v2/hateoas/clasificaciones/" + juego.getClasificacionId(),
                        "clasificacion")
        );
    }
}
