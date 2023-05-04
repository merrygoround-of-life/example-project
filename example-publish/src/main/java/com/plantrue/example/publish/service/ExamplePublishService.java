package com.plantrue.example.publish.service;

import com.plantrue.example.common.model.KafkaMessage;
import reactor.core.publisher.Mono;

public interface ExamplePublishService {
    Mono<Void> send(KafkaMessage message);
}
