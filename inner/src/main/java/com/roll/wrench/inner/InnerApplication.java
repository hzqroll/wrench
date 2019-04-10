package com.roll.wrench.inner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = "classpath:dubbo.xml")
public class InnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InnerApplication.class, args);
    }

}
