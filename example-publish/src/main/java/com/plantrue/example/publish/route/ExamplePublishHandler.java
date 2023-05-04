package com.plantrue.example.publish.route;

import com.plantrue.example.common.model.KafkaMessage;
import com.plantrue.example.publish.service.ExamplePublishService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ExamplePublishHandler {

    private final ExamplePublishService service;

    public ExamplePublishHandler(ExamplePublishService service) {
        this.service = service;
    }

    Mono<ServerResponse> send(ServerRequest request) {
        return request.bodyToMono(KafkaMessage.class)
                .flatMap(message -> ServerResponse.ok().body(service.send(message), KafkaMessage.class));
    }
}
