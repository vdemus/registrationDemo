package com.registration.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RegistrationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationDemoApplication.class, args);
    }
}
