package com.plantrue.example.db.repository;

import com.plantrue.example.db.model.CatFact;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CatFactRepository extends ReactiveCrudRepository<CatFact, Long> {
    Mono<Boolean> existsByFactKey(Long factKey);
    @Query("select t.* from cat_fact t where t.len <= :maxLen order by rand() limit 1")
    Mono<CatFact> find(Long maxLen);
}
