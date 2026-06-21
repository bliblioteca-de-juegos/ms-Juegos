package com.biblioteca.Ms_Juegos.Config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}

	@Bean
	public WebClient categoriaWebClient(@LoadBalanced WebClient.Builder builder) {
		return builder.clone().baseUrl("http://ms-categoria").build();
	}

	@Bean
	public WebClient clasificacionWebClient(@LoadBalanced WebClient.Builder builder) {
		return builder.clone().baseUrl("http://ms-clasificacion").build();
	}

}
