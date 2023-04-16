package com.remindme.twilio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SmsRequest {
    @NotBlank
    private final String phoneNUmber;
    @NotBlank
     private final String message;
    public SmsRequest(@JsonProperty(value = "phoneNumber") String phoneNUmber,
                      @JsonProperty(value = "message") String message){
        this.message=message;
        this.phoneNUmber=phoneNUmber;
    }

}

