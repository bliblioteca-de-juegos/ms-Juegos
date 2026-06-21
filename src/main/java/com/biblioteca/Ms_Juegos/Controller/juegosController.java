package com.biblioteca.Ms_Juegos.Controller;

import com.biblioteca.Ms_Juegos.Service.JuegosService;
import com.biblioteca.Ms_Juegos.Dto.JuegosResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/Juegos", "/api/juegos"})
@Tag(name="juegos", description = "operaciones relacionadas con ls juegos ")
@RequiredArgsConstructor
public class juegosController {

    @Autowired
    private final JuegosService juegosService;

    @GetMapping
    @Operation(summary = "Obtener todas los juegos", description = "obtiene una lista de todos los juegos")
    public List<JuegosResponseDTO> obtenerTodos(){
        return juegosService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JuegosResponseDTO> obtenerPorId(@PathVariable Long id){
        return juegosService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/categoria/{cateId}")
    public List<JuegosResponseDTO> obtenerPorCategoria(@PathVariable Long cateId){
        return juegosService.obtenerPorCategoria(cateId);
    }

    @GetMapping("/buscar")
    public List<JuegosResponseDTO> buscarPorTitulo(@RequestParam String titulo){
        return juegosService.buscarPorTitulo(titulo);
    }

    @GetMapping("/precio")
    public List<JuegosResponseDTO> porPrecio(@RequestParam int min, @RequestParam int max){
        return juegosService.buscarPorPrecio(min,max);
    }

    @PostMapping
    public ResponseEntity<JuegosResponseDTO> crear(@Valid @RequestBody JuegosResponseDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(juegosService.Guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JuegosResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody JuegosResponseDTO dto){
        return juegosService.actualizar(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JuegosResponseDTO> eliminar(@PathVariable Long id){
        juegosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}
