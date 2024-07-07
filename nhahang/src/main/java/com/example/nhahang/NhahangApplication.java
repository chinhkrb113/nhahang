package com.example.nhahang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class SinhnhatApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(SinhnhatApplication.class, args);
//	}
//
//}
@SpringBootApplication
public class NhahangApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(NhahangApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NhahangApplication.class);
    }

}