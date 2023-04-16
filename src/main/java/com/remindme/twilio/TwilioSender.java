package com.remindme.twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TwilioSender {
    TwilioConfiguration twilioConfiguration;

    public  void sendSms(SmsRequest smsRequest) {
        PhoneNumber to=new PhoneNumber("1" + smsRequest.getPhoneNUmber());
        PhoneNumber from=new PhoneNumber(twilioConfiguration.getTrialNumber());
        String message=smsRequest.getMessage();
        MessageCreator creator= Message.creator(to,from,message);
        creator.create();
        log.info("Message " + message + "sent to " + to.getEndpoint() + "from "  + from.getEndpoint());
    }
}
