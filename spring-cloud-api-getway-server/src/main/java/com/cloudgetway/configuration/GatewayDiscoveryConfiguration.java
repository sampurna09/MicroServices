package com.cloudgetway.configuration;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
public class GatewayDiscoveryConfiguration {
 
//	   @Bean
//	    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//	        return builder.routes()
//	                .route(r -> r.path("/currency-converter-feign/**")
//	                        .uri("http://localhost:8100/")
//	                        .id("currencyExchange"))
//
//	                .route(r -> r.path("/consumer/**")
//	                        .uri("http://localhost:8082/error")
//	                        .id("consumerModule"))
//	                .build();
//	    }
}
