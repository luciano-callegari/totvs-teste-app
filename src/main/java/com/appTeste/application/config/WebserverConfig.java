package com.appTeste.application.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "com.appTeste")
@EntityScan(basePackages = "com.appTeste")
public class WebserverConfig {
}
