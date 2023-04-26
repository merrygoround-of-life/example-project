package com.plantrue.example.api.service;

import com.plantrue.example.common.model.CatFactDto;
import com.plantrue.example.api.model.RequestMessage;
import com.plantrue.example.api.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Random;

import static com.plantrue.example.common.model.CatFactDto.QUERY_PARAM_MAX_LENGTH;

@Service
public class ExampleApiServiceV1 implements ExampleApiService {

    @Value("${example.catfact.reuse-ratio}")
    private Float catfactReuseRatio;

    @Value("${example.catfact.base-url}")
    private String catfactBaseUrl;

    @Value("${example.catfact.service-uri}")
    private String catfactServiceUri;

    @Value("${example.catfact.max-length}")
    private Integer catfactMaxLength;

    @Value("${example.db.base-url}")
    private String dbBaseUrl;

    @Value("${example.db.uri-setcatfact}")
    private String dbSetCatFactUri;

    @Value("${example.db.uri-getcatfact}")
    private String dbGetCatFactUri;

    private WebClient dbClient;

    private WebClient catfactClient;

    public ExampleApiServiceV1() {
    }

    @PostConstruct
    public void init() {
        this.dbClient = WebClient.create(dbBaseUrl);
        this.catfactClient = WebClient.create(catfactBaseUrl);
    }

    @Override
    public Mono<ResponseMessage> echo(RequestMessage message) {
        return Mono.just(new ResponseMessage(message.getMsg()));
    }

    @Override
    public Mono<ResponseMessage> catfact() {
        return Mono.just(new Random().nextFloat() <= catfactReuseRatio)
                .filter(exist -> exist)
                .flatMap(exist -> dbClient
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path(dbGetCatFactUri)
                                .queryParam(QUERY_PARAM_MAX_LENGTH, catfactMaxLength.toString())
                                .build()
                        )
                        .retrieve()
                        .bodyToMono(CatFactDto.class)
                        .map(CatFactDto::getFact)
                        .map(ResponseMessage::new)
                        .switchIfEmpty(Mono.defer(this::fetch))
                )
                .switchIfEmpty(Mono.defer(this::fetch));
    }

    private Mono<ResponseMessage> fetch() {
        return catfactClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(catfactServiceUri)
                        .queryParam(QUERY_PARAM_MAX_LENGTH, catfactMaxLength.toString())
                        .build()
                )
                .retrieve()
                .bodyToMono(CatFactDto.class)
                .flatMap(catFactDto -> dbClient
                        .post()
                        .uri(uriBuilder -> uriBuilder
                                .path(dbSetCatFactUri)
                                .queryParam(QUERY_PARAM_MAX_LENGTH, catfactMaxLength.toString())
                                .build()
                        )
                        .bodyValue(catFactDto)
                        .retrieve()
                        .bodyToMono(CatFactDto.class)
                        .map(CatFactDto::getFact)
                        .map(ResponseMessage::new)
                );
    }
}

