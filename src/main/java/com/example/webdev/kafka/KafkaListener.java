package com.example.webdev.kafka;

import java.util.concurrent.CountDownLatch;


public class KafkaListener {
    private final CountDownLatch latch = new CountDownLatch(1);

    @org.springframework.kafka.annotation.KafkaListener(id = "foo", topics = "annotated1")
    public void listen1(String foo) {
        this.latch.countDown();
    }
}
