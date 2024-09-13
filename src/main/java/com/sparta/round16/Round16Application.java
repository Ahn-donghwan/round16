package com.sparta.round16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Round16Application {

    public static void main(String[] args) {
        SpringApplication.run(Round16Application.class, args);
    }

}
