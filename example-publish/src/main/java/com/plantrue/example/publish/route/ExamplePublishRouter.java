package com.plantrue.example.publish.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class ExamplePublishRouter {

    @Value("${example.publish.uri-send}")
    private String uriSend;

    private final ExamplePublishHandler handler;

    public ExamplePublishRouter(ExamplePublishHandler handler) {
        this.handler = handler;
    }

    @Bean
    RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(POST(uriSend), handler::send);
    }
}
