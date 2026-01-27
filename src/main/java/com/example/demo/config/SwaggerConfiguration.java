/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author marco-romero
 */
@Configuration
public class SwaggerConfiguration {

    /*Ruta para acceder a la documentacion de apis
            http://localhost:8080/swagger-ui/index.html
        
        Nota: La documentacion de las apis, se encuentra limitada solo para
              documentar las apis que esten implementadas en el package 'controller'
     */
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("FIRMA")
                        .description("VERSION 1")
                        .version("1.0")
                        .termsOfService("/terms") //Ruta donde se encuentran los terminos y servicio de la aplicacion
                        .contact(new Contact()
                                .name("ADIP")
                                .url("https://adip.cdmx.gob.mx/")
                                .email("")
                        ).license(new License()
                                .name("LICENSE")
                                .url("LICENSE URL")
                        )
                );
    }
}
