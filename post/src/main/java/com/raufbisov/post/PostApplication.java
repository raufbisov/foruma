package com.raufbisov.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.raufbisov.auth.AuthClient;
import com.raufbisov.interaction.InteractionClient;

@SpringBootApplication
@EnableFeignClients(
    clients = {
        AuthClient.class,
        InteractionClient.class
    }
)
public class PostApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);
    }
}
