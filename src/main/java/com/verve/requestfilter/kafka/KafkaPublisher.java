package com.verve.requestfilter.kafka;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

@Service
public class KafkaPublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;
    @Value("${spring.kafka.producer.topic}")
    private String kafkaTopic;

    public void publishMessage(String message){
        CompletableFuture<SendResult<String, Object>> future = template.send(kafkaTopic, message);
        future.whenComplete((result, ex)->{
            //log the result from kafka
        });
    }

}
