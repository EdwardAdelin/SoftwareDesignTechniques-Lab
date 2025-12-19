package com.example.student_service;// Make sure this matches your package structure

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class StudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

	// This is the bean we added for communication
	@Bean
	@LoadBalanced // Aceasta adnotare permite folosirea numelor de servicii in loc de IP-uri
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}