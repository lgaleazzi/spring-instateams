package com.instateams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Disabling autoconfiguration of thymeleaf so we can use Thymeleaf 3
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class})
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }
}