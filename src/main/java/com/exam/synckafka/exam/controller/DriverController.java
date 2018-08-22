package com.exam.synckafka.exam.controller;

import com.exam.synckafka.exam.model.Driver;
import com.exam.synckafka.exam.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * Created by birju.s on 23-08-2018.
 */
@RestController
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping(value = "/release", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void releaseADriver(@RequestBody Driver request) throws InterruptedException, ExecutionException {
        //helper method to release driver.. which will add driver again to the pool of drivers
        //todo : add validations
        driverService.releaseDriver(request);

    }
}