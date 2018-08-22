package com.exam.synckafka.exam.controller;

import com.exam.synckafka.exam.model.JourneyRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * Created by birju.s on 23-08-2018.
 */

@RestController
public class JourneyRequestController {

    @Autowired
    ReplyingKafkaTemplate<String, JourneyRequest,JourneyRequest> kafkaTemplate;

    @Value("${kafka.topic.request-topic}")
    String requestTopic;

    @Value("${kafka.topic.requestreply-topic}")
    String requestReplyTopic;

    @ResponseBody()
    @PostMapping(value="/journey",produces= MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
    public JourneyRequest requestADriver(@RequestBody JourneyRequest request) {

        //todo: Have to handle Timeout exception via custom error message : currently handled by sprint boot itself

        // create producer record
        ProducerRecord<String, JourneyRequest> record = new ProducerRecord<String, JourneyRequest>(requestTopic, request);
        // set reply topic in header
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));
        // post in kafka topic
        RequestReplyFuture<String, JourneyRequest, JourneyRequest> sendAndReceive = kafkaTemplate.sendAndReceive(record);

        // confirm if producer produced successfully
        SendResult<String, JourneyRequest> sendResult = null;

        try {

            sendResult = sendAndReceive.getSendFuture().get();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ExecutionException e) {

            System.out.println("Error in sending the request");

            e.printStackTrace();
        }

        //print all headers
        sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));


        // get consumer record
        ConsumerRecord<String, JourneyRequest> consumerRecord = null;

        try {

            consumerRecord = sendAndReceive.get();

        } catch (InterruptedException e) {

            System.out.println("Error in getting the consumer Record");

        } catch (ExecutionException e) {

            System.out.println("Error in assigning a driver. Please try after some time.");

        }
        // return consumer value
        return consumerRecord.value();
    }
}
