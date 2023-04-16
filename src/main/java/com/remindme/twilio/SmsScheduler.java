package com.remindme.twilio;

import com.remindme.entity.Occasion;
import com.remindme.entity.Text;
import com.remindme.repository.OccasionRepository;
import com.remindme.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class SmsScheduler {
    OccasionRepository occasionRepository;
    TwilioSender twilioSender;
    TextService textService;

    @Scheduled(cron ="0 0/30 8-13 * * *" )
    public void checkOccasions() {
        LocalDate today=LocalDate.now();
        int month=today.getMonthValue();
        int day=today.getDayOfMonth();
        System.out.println("month: "+ month);
        System.out.println("day: " + day);
        List<Occasion> todayOccasions=occasionRepository.findOccasionsByOccasionDate(month,day);
        for (Occasion occasion : todayOccasions){
            boolean wasSent=false;
            List<Text> sentTexts=occasion.getTexts();
            if (sentTexts.isEmpty()) {
                System.out.println("SentText is empty");
            }else {
                innerLoop:
                for (Text sentText :sentTexts) {
                    if(sentText.getSentTime().getDayOfMonth()==day &&
                    sentText.getSentTime().getMonthValue()==month){
                        wasSent=true;
                        break innerLoop;
                    }
                }
            }
            if (wasSent){
                continue;
            }else {
                String message="Reminder: Today is " + occasion
                        .getFriend().getFirstName() + " " + occasion
                        .getFriend().getLastName() + " " + occasion
                        .getOccasionName() + ". Make sure text them at " +
                        occasion.getFriend().getPhoneNumber() + ".";
                String phoneNumber=occasion
                        .getFriend()
                        .getUserAccount()
                        .getUserProfile()
                        .getPhoneNumber();
                Text newText=new Text(LocalDateTime.now(), message,phoneNumber);
                SmsRequest smsRequest=new SmsRequest(phoneNumber,message);
                twilioSender.sendSms(smsRequest);
                textService.addText(occasion.getId(), newText);
            }
        }
    }
}
