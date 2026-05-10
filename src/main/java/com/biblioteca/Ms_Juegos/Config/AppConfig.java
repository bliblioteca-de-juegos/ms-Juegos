package com.biblioteca.Ms_Juegos.Config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Value("${ms.categoria.url}")
    private String categoriasurl;

    @Value("${ms.clasificacion.url}")
    private String clasificacionUrl;

    @Bean
    public WebClient categoriaWebClient() {
        return WebClient.builder().baseUrl(categoriasurl).build();
    }

    @Bean
    public WebClient clasificacionWebClient() {
        return WebClient.builder().baseUrl(clasificacionUrl).build();
    }

}

