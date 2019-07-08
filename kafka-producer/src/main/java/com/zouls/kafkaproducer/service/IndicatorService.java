package com.zouls.kafkaproducer.service;

public interface IndicatorService {
    void sendMessage(String topic, String data);
}