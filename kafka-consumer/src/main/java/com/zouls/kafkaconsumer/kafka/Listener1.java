package com.zouls.kafkaconsumer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Listener1 {

    @KafkaListener(groupId = "${kafka.consumer.group.id.group1}", topics = "${kafka.topic.topic1}",
            containerFactory = "batchFactory")
    public void receiveGpsPosition(List<String> list, Acknowledgment ack) {
        try {
            //do something
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Meet an error", e);
        }
    }
}