package com.exam.synckafka.exam.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by birju.s on 23-08-2018.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customerId",
        "driverId"
})
public class JourneyRequest {

    @JsonProperty("customerId")
    private int customerId;

    @JsonProperty("driverId")
    private int driverId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("errorCode")
    public String getErrorCode() {
        return errorCode;
    }

    @JsonProperty("errorCode")
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


    //todo: once this works, add jouney for a customer ( Later )
    //journey;



    @JsonProperty("customerId")
    public int getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("driverId")
    public int getDriverId() {
        return driverId;
    }

    @JsonProperty("driverId")
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        //todo : change it to log4j
        System.out.println("CustomerId " + customerId + " is assigned driver :" + driverId );
        return super.toString();
    }
}
