package com.plantrue.example.db.route;

import com.plantrue.example.common.model.CatFactDto;
import com.plantrue.example.db.service.ExampleDbService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.plantrue.example.common.model.CatFactDto.QUERY_PARAM_MAX_LENGTH;

@Component
public class ExampleDbHandler {

    private final ExampleDbService service;

    public ExampleDbHandler(ExampleDbService service) {
        this.service = service;
    }

    Mono<ServerResponse> setCatFact(ServerRequest request) {
        return request.bodyToMono(CatFactDto.class)
                .flatMap(catFactDto ->
                        ServerResponse.ok().body(
                                service.setCatFact(catFactDto),
                                CatFactDto.class
                        )
                );
    }

    Mono<ServerResponse> getCatFact(ServerRequest request) {
        return ServerResponse.ok().body(
                service.getCatFact(
                        request
                                .queryParam(QUERY_PARAM_MAX_LENGTH).map(Long::parseLong)
                                .orElse(1000L)
                ),
                CatFactDto.class
        );
    }
}
