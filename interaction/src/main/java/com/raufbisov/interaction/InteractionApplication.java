package com.raufbisov.interaction;

import com.raufbisov.auth.AuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = { AuthClient.class })
public class InteractionApplication {

  public static void main(String[] args) {
    SpringApplication.run(InteractionApplication.class, args);
  }
}
