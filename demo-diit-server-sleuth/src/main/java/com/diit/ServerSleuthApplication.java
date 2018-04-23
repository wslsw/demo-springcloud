package com.diit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ServerSleuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerSleuthApplication.class, args);
	}
}
