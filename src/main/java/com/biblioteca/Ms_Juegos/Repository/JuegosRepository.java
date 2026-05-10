package com.biblioteca.Ms_Juegos.Repository;

import com.biblioteca.Ms_Juegos.Modelo.Juegos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JuegosRepository  extends JpaRepository<Juegos, Long> {

    List<Juegos> findByCategoriaId(Long categoriaId);

    @Query("SELECT m FROM Juegos m WHERE LOWER(m.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Juegos> buscarPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT m FROM Juegos m WHERE LOWER(m.descripcion) LIKE LOWER(CONCAT('%', :descripcion, '%'))")
    List<Juegos> buscarPorDescripcion(@Param("descripcion") String descripcion);

    @Query("SELECT m FROM Juegos m WHERE m.precio BETWEEN :min AND :max ORDER BY m.precio")
    List<Juegos> findByPrecioBetween(@Param("min") int min, @Param("max") int max);


}
