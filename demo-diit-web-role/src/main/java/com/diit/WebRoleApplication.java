package com.diit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@Configuration
@MapperScan(basePackages = "com.diit.service.role.impl")
public class WebRoleApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebRoleApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WebRoleApplication.class, args);
	}
	
	
	/*@Autowired
	private RestTemplate restTemplate;
    
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
	public AlwaysSampler defaultSampler(){
		return new AlwaysSampler();
	}
    
    @RequestMapping("/role/listUsers")
    public @ResponseBody Map<String, Object> listUsersWithPage(Model model, Integer page, Integer limit) {
    	
    	Object[] arr = new Object[]{page, limit};
				
        Map<String, Object> lists = restTemplate.getForObject("http://SERVICE-USER/listUsersWithPage?start={page}&length={limit}",Map.class,arr);

        return lists;
    }*/
}
