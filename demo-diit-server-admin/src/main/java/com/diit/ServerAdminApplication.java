package com.diit;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class ServerAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerAdminApplication.class, args);
	}
}
