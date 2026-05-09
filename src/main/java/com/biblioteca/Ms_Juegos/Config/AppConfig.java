package com.biblioteca.Ms_Juegos.Config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Value("${ms.categoria.url}")
    private String categoriasurl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(categoriasurl).build();
    }


}


