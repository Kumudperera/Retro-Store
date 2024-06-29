package com.retroStore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(name = "apps.properties", value = "classpath:apps.properties")
public class RetroStoreApplication extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RetroStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
