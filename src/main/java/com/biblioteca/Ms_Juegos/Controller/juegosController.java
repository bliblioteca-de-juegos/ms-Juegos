package com.biblioteca.Ms_Juegos.Controller;
import com.biblioteca.Ms_Juegos.Service.JuegosService;
import com.biblioteca.Ms_Juegos.Dto.JuegosResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping({"/api/v2/Juegos", "/api/v2/juegos"})
@Tag(name = "Juegos", description = "Operaciones relacionadas con el catalogo de juegos")
public class juegosController {
    @Autowired
    private JuegosService juegosService;
    @GetMapping
    @Operation(summary = "Listar todos los juegos", description = "Obtiene una lista de todos los juegos")
    public List<JuegosResponseDTO> obtenerTodos(){
        return juegosService.obtenerTodas();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un juego por ID")
    public ResponseEntity<JuegosResponseDTO> obtenerPorId(@PathVariable Long id){
        return juegosService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{cateId}")
    @Operation(summary = "Listar juegos por categoria")
    public List<JuegosResponseDTO> obtenerPorCategoria(@PathVariable Long cateId){
        return juegosService.obtenerPorCategoria(cateId);
    }
    @GetMapping("/buscar")
    @Operation(summary = "Buscar juegos por titulo")
    public List<JuegosResponseDTO> buscarPorTitulo(@RequestParam String titulo){
        return juegosService.buscarPorTitulo(titulo);
    }
    @GetMapping("/precio")
    @Operation(summary = "Buscar juegos por rango de precio")
    public List<JuegosResponseDTO> porPrecio(@RequestParam int min, @RequestParam int max){
        return juegosService.buscarPorPrecio(min,max);
    }
    @PostMapping
    @Operation(summary = "Crear un juego")
    public ResponseEntity<JuegosResponseDTO> crear(@Valid @RequestBody JuegosResponseDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(juegosService.Guardar(dto));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un juego")
    public ResponseEntity<JuegosResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody JuegosResponseDTO dto){
        return juegosService.actualizar(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un juego")
    public ResponseEntity<JuegosResponseDTO> eliminar(@PathVariable Long id){
        juegosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
