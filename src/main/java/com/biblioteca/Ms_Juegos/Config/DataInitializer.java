package com.biblioteca.Ms_Juegos.Config;

import org.springframework.beans.factory.annotation.Autowired;

import com.biblioteca.Ms_Juegos.Modelo.Juegos;
import com.biblioteca.Ms_Juegos.Repository.JuegosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private JuegosRepository repository;
    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            log.info(">>>> Juegos ya cargados. Se omite inicializacion");
            return;
        }
        log.info(">>>> Cargando juegos iniciales...");
        repository.save(new Juegos(null, "My hero Ultrarumble",            "Batlle royal entre heroes y villanos gana el ultimo grupo en pie", 00.00, 1L, 3L));
        repository.save(new Juegos(null, "God Of War",                     "Aventura de un antiguo guerro en las tierras nordicas",            52.00, 1L, 5L));
        repository.save(new Juegos(null, "Sonic Racing CrossWorlds",       "Juegos de carreas en muchas pistas y con muchos personnajes", 38.50, 2L, 2L));
        repository.save(new Juegos(null, "Balatro",                        "juego de cartas rouglike basado en el poker", 29.99, 2L, 3L));
        log.info(">>> 5 juegos cargadas OK.");
    }
}
