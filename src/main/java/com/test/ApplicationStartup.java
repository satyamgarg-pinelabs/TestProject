package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@SpringBootConfiguration
@ComponentScan("com.test.*")
public class ApplicationStartup extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStartup.class, args);
    }

}
