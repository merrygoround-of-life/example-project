package com.plantrue.example.common.model;

public class CatFactDto {
    private String fact;

    private Long length;

    public static String QUERY_PARAM_MAX_LENGTH = "max_length";

    public CatFactDto() {
    }

    public String getFact() {
        return fact;
    }

    public CatFactDto setFact(String fact) {
        this.fact = fact;
        return this;
    }

    public Long getLength() {
        return length;
    }

    public CatFactDto setLength(Long length) {
        this.length = length;
        return this;
    }
}
