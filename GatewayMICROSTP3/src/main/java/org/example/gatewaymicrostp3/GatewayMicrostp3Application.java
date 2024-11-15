package org.example.gatewaymicrostp3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayMicrostp3Application {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMicrostp3Application.class, args);
    }
    @Bean
    RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/customers/**").uri("lb://CUSTOMER-SERV"))
                .route(r->r.path("/products/**").uri("lb://INVENTORY-SERVICE"))
                .build();

    }
}
