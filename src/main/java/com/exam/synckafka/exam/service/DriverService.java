package com.exam.synckafka.exam.service;

import com.exam.synckafka.exam.model.Driver;

/**
 * Created by birju.s on 23-08-2018.
 */

public interface DriverService  {

    public Driver requestADriver() throws InterruptedException;

    public void releaseDriver(Driver d) throws InterruptedException;

    public void list();
}
