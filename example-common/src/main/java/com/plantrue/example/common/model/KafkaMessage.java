package com.plantrue.example.common.model;

public class KafkaMessage {
    private String topic;

    private String key;

    private String value;

    public KafkaMessage() {
    }

    public String getTopic() {
        return topic;
    }

    public KafkaMessage setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getKey() {
        return key;
    }

    public KafkaMessage setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public KafkaMessage setValue(String value) {
        this.value = value;
        return this;
    }
}