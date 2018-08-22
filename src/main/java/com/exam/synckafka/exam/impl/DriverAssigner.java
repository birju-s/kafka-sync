package com.exam.synckafka.exam.impl;

import com.exam.synckafka.exam.model.Driver;
import com.exam.synckafka.exam.model.JourneyRequest;
import com.exam.synckafka.exam.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created by birju.s on 23-08-2018.
 */
@Component
public class DriverAssigner {

    private final DriverService driverService;

    @Autowired
    public DriverAssigner(DriverService driverService) {
        this.driverService = driverService;
    }

    @KafkaListener(topics = "${kafka.topic.request-topic}")
    @SendTo
    public JourneyRequest listen(JourneyRequest request) throws InterruptedException {
        //todo : handle timeouts and send proper response
        Driver d = driverService.requestADriver();

        request.setDriverId(d.getId());
        return request;
    }
}
