package com.inclulink;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.inclulink")
@EntityScan(basePackages = "com.inclulink.core.entidades")
@ComponentScan(basePackages = "com.inclulink")
@EnableJpaRepositories(basePackages = "com.inclulink")
public class IncluLinkApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(IncluLinkApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}