package com.raufbisov.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.raufbisov.auth.AuthClient;
import com.raufbisov.interaction.InteractionClient;

@SpringBootApplication
@EnableFeignClients(
    basePackageClasses = {
        AuthClient.class,
        InteractionClient.class
    }
)
public class CommentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentApplication.class, args);
    }
}
