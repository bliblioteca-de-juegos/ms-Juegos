package com.Bliblioteca.Ms_juegos.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "juegos")
public class juegos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
