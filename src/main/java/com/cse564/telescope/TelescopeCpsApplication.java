package com.cse564.telescope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class TelescopeCpsApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TelescopeCpsApplication.class, args);
        System.out.println("\n===========================================");
        System.out.println("Telescope CPS Simulator Started");
        System.out.println("Dashboard: http://localhost:8080");
        System.out.println("API Docs: http://localhost:8080/api/status");
        System.out.println("===========================================\n");
    }
}