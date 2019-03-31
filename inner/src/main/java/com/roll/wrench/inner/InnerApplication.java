package com.roll.wrench.inner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = "classpath:*.xml")
@ComponentScan(basePackages = {"com.roll.wrench.inner.*"})
public class InnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InnerApplication.class, args);
    }

}
