package com.example.demo.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestClientApplication {

	private static final Logger log = LoggerFactory.getLogger(RestClientApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {

			List<ClientHttpRequestInterceptor> interceptors = new ArrayList();
			ClientHttpRequestInterceptor interceptor = new ClientHttpRequestInterceptor() {

				@Override
				public ClientHttpResponse intercept(HttpRequest request, byte[] body,
						ClientHttpRequestExecution execution) throws IOException {
					// TODO Auto-generated method stub
					
					
					
					//Build new request
					Request.Builder builder = request.newBuilder();
					builder.header("Accept", "application/json"); //if necessary, say to consume JSON
					
					return null;
					
				}
				
			};
			
			interceptors.add(interceptor);
			
			restTemplate.setInterceptors(interceptors);
					
			
			
			// 1 – List public repositories.
			String[] repositories = restTemplate.getForObject("https://localhost:8080/products",
					String[].class);
			
			
			
			

			

		};
	}

}
