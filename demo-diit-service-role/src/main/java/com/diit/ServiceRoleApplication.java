package com.diit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.diit.common.wrapper.BaseMapper;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = "com.diit.core.role.mapper", markerInterface = BaseMapper.class)
public class ServiceRoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRoleApplication.class, args);
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
