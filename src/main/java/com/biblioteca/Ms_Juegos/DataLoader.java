package com.biblioteca.Ms_Juegos;

import com.biblioteca.Ms_Juegos.Modelo.Juegos; // Importamos tu modelo
import com.biblioteca.Ms_Juegos.Repository.JuegosRepository;
import net.datafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Profile("dev")
@Slf4j
public class DataLoader implements CommandLineRunner {

    @Autowired
    private JuegosRepository juegosRepository;

    @Override
    public void run(String... args) throws Exception {
        // Inicializamos Faker (puedes usar Locale.forLanguageTag("es") para datos en español)
        Faker faker = new Faker(new Locale("es"));

        // Opcional: Limpiamos la tabla para no duplicar datos cada vez que reinicias
        juegosRepository.deleteAll();

        // Generar 10 juegos aleatorios
        for (int i = 0; i < 10; i++) {
            Juegos juego = new Juegos();

            // Nota: No seteamos el ID porque es IDENTITY (autoincremental)
            juego.setTitulo(faker.videoGame().title());
            juego.setDescripcion(faker.lorem().sentence(10)); // Genera una frase corta
            juego.setPrecio(faker.number().randomDouble(2, 10, 100));
            juego.setCategoriaId((long) faker.number().numberBetween(1, 5));
            juego.setClasificacionId((long) faker.number().numberBetween(1, 4));

            // Guardamos en la base de datos
            juegosRepository.save(juego);
        }

        log.info("Base de datos inicializada con 10 juegos");
    }
}
