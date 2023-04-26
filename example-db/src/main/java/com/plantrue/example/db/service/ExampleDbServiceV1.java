package com.plantrue.example.db.service;

import com.google.common.hash.Hashing;
import com.plantrue.example.common.model.CatFactDto;
import com.plantrue.example.db.model.CatFact;
import com.plantrue.example.db.repository.CatFactRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Service
public class ExampleDbServiceV1 implements ExampleDbService {

    private final CatFactRepository catFactRepository;

    public ExampleDbServiceV1(CatFactRepository catFactRepository) {
        this.catFactRepository = catFactRepository;
    }

    @Override
    public Mono<CatFactDto> setCatFact(CatFactDto catFactDto) {
        String fact = catFactDto.getFact();
        Long factKey = Long.valueOf(Hashing.crc32().hashString(fact, StandardCharsets.UTF_8).asInt());

        return catFactRepository.existsByFactKey(factKey)
                .filter(exist -> exist)
                .map(exist -> catFactDto)
                .switchIfEmpty(Mono.defer(() ->
                        catFactRepository.save(
                                new CatFact()
                                        .setFactKey(factKey)
                                        .setFact(fact)
                                        .setLen(catFactDto.getLength())
                        ).map(catFact ->
                                new CatFactDto()
                                        .setFact(catFact.getFact())
                                        .setLength(catFact.getLen())
                        )
                ));
    }

    @Override
    public Mono<CatFactDto> getCatFact(Long maxLen) {
        return catFactRepository.find(maxLen)
                .map(catFact ->
                        new CatFactDto()
                                .setFact(catFact.getFact())
                                .setLength(catFact.getLen())
                );
    }
}

