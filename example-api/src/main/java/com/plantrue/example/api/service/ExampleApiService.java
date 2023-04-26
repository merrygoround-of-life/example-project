package com.plantrue.example.api.service;

import com.plantrue.example.api.model.RequestMessage;
import com.plantrue.example.api.model.ResponseMessage;
import reactor.core.publisher.Mono;

public interface ExampleApiService {
    Mono<ResponseMessage> echo(RequestMessage message);
    Mono<ResponseMessage> catfact();
}
