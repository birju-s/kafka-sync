package com.exam.synckafka.exam.impl;

import com.exam.synckafka.exam.model.Driver;
import com.exam.synckafka.exam.service.DriverService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by birju.s on 23-08-2018.
 */
@Service
@Component
public class DriverServiceImpl implements DriverService {

    private LinkedBlockingQueue<Driver> driverQueue = new LinkedBlockingQueue<Driver>();

    public DriverServiceImpl() throws InterruptedException {
        //Todo : this is just to test functionality :)
        Driver d1 = new Driver(); d1.setId(1);driverQueue.put(d1);
        Driver d2 = new Driver(); d2.setId(2);driverQueue.put(d2);
        Driver d3 = new Driver(); d3.setId(3);driverQueue.put(d3);
        Driver d4 = new Driver(); d4.setId(4);driverQueue.put(d4);
        Driver d5 = new Driver(); d5.setId(5);driverQueue.put(d5);
        Driver d6 = new Driver(); d6.setId(6);driverQueue.put(d6);
        Driver d7 = new Driver(); d7.setId(7);driverQueue.put(d7);
        Driver d8 = new Driver(); d8.setId(8);driverQueue.put(d8);
        Driver d9 = new Driver(); d9.setId(9);driverQueue.put(d9);
        Driver d10 = new Driver(); d9.setId(10);driverQueue.put(d10);

    }

    @Override
    public Driver requestADriver() throws InterruptedException {
        //todo : if you require other behaviour then we can implement here..
        //Right now, i have taken blocking queue..and used the top driver from there.

        Driver returnDriver = driverQueue.take();

        //todo : add validations while returning + return null and handle
        return returnDriver;

    }

    @Override
    public void releaseDriver(Driver d) throws InterruptedException {
        //todo : have to check for duplicates.. assuming right now that we have unique
        //todo ; as this is a helper method as of now..which will help to test
        driverQueue.put(d);
        list();
    }

    @Override
    public void list() {
        for ( Driver d : driverQueue){
          System.out.print(d.getId() + " ");
        }
    }
}
