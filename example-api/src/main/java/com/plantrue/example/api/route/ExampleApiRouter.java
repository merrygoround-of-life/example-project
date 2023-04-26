package com.plantrue.example.api.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class ExampleApiRouter {

    @Value("${example.api.uri-echo}")
    private String uriEcho;

    @Value("${example.api.uri-catfact}")
    private String uriCatFact;

    private final ExampleApiHandler handler;

    public ExampleApiRouter(ExampleApiHandler handler) {
        this.handler = handler;
    }

    @Bean
    RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(POST(uriEcho), handler::echo)
                .andRoute(GET(uriCatFact), handler::catfact);
    }
}
