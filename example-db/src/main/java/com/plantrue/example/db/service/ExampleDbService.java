package com.plantrue.example.db.service;

import com.plantrue.example.common.model.CatFactDto;
import reactor.core.publisher.Mono;

public interface ExampleDbService {
    Mono<CatFactDto> setCatFact(CatFactDto catFactDto);
    Mono<CatFactDto> getCatFact(Long maxLen);
}
