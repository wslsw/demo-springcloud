package com.diit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
@EnableHystrixDashboard
public class ServerTurbineApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ServerTurbineApplication.class, args);
		new SpringApplicationBuilder(ServerTurbineApplication.class).web(true).run(args);
	}
}
