package com.remindme.twilio;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/twilio")
public class TwilioController {
    TwilioSender twilioSender;
    @PostMapping
    public void sendSms(@NotNull @RequestBody @Valid
                        SmsRequest smsRequest) {
        twilioSender.sendSms(smsRequest);
    }
}
