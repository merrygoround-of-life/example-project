package com.plantrue.example.api.route;

import com.plantrue.example.api.model.RequestMessage;
import com.plantrue.example.api.model.ResponseMessage;
import com.plantrue.example.api.service.ExampleApiService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ExampleApiHandler {

    private final ExampleApiService service;

    public ExampleApiHandler(ExampleApiService service) {
        this.service = service;
    }

    Mono<ServerResponse> echo(ServerRequest request) {
        return request.bodyToMono(RequestMessage.class)
                .flatMap(message -> ServerResponse.ok().body(service.echo(message), ResponseMessage.class));
    }

    Mono<ServerResponse> catfact(ServerRequest request) {
        return ServerResponse.ok().body(service.catfact(), ResponseMessage.class);
    }
}
