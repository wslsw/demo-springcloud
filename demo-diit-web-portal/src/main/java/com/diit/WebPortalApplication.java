package com.diit;

import com.diit.common.utils.JedisClient;
import com.diit.common.utils.impl.JedisClientSingle;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients
@Configuration
@EnableHystrix
@MapperScan(basePackages = "com.diit.service.portal.impl")
public class WebPortalApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebPortalApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WebPortalApplication.class, args);
	}

	@Bean
	public JedisClient jedisClient() { return new JedisClientSingle(); }
}
