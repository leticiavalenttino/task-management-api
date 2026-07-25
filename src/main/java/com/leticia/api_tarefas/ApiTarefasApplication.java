package com.leticia.api_tarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiTarefasApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiTarefasApplication.class, args);
    }
}