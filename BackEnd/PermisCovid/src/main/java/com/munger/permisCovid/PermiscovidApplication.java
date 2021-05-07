package com.munger.permisCovid;

import com.munger.permisCovid.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class PermiscovidApplication implements CommandLineRunner {

    private static SystemService service2;
    @Autowired
    SystemService service;

    public static void main(String[] args) {
        SpringApplication.run(PermiscovidApplication.class, args);

    }


    @Override
    public void run(String... args) throws Exception {

    }
}
