package com.plantrue.example.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("cat_fact")
public class CatFact {
    @Id
    public Long id;

    public Long len;

    public Long factKey;

    public String fact;

    public CatFact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLen() {
        return len;
    }

    public CatFact setLen(Long len) {
        this.len = len;
        return this;
    }

    public Long getFactKey() {
        return factKey;
    }

    public CatFact setFactKey(Long factKey) {
        this.factKey = factKey;
        return this;
    }

    public String getFact() {
        return fact;
    }

    public CatFact setFact(String fact) {
        this.fact = fact;
        return this;
    }
}