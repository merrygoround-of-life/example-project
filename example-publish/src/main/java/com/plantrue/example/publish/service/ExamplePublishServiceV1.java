package com.plantrue.example.publish.service;

import com.plantrue.example.common.model.KafkaMessage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaOutbound;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExamplePublishServiceV1 implements ExamplePublishService {

    @Value("${example.kafka.bootstrap-servers}")
    private String kafkaBootstrapServers;

    KafkaOutbound<String, String> kafkaOutbound;

    @PostConstruct
    public void init() {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        SenderOptions<String, String> senderOptions = SenderOptions.create(producerProps);
        kafkaOutbound = KafkaSender.create(senderOptions).createOutbound();
    }

    public ExamplePublishServiceV1() {
    }

    @Override
    public Mono<Void> send(KafkaMessage message) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(
                message.getTopic(),
                message.getKey(),
                message.getValue()
        );

        return kafkaOutbound.send(Mono.just(producerRecord)).then();
    }
}

