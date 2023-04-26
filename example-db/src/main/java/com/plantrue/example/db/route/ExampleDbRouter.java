package com.plantrue.example.db.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Optional;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class ExampleDbRouter {

    @Value("${example.db.uri-setcatfact}")
    private String uriSetCatFact;

    @Value("${example.db.uri-getcatfact}")
    private String uriGetCatFact;

    private final ExampleDbHandler handler;

    public ExampleDbRouter(ExampleDbHandler handler) {
        this.handler = handler;
    }

    @Bean
    RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(POST(uriSetCatFact), handler::setCatFact)
                .andRoute(GET(uriGetCatFact), handler::getCatFact);
    }
}
