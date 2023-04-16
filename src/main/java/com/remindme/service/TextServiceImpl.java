package com.remindme.service;

import com.remindme.entity.Friend;
import com.remindme.entity.Occasion;
import com.remindme.entity.Text;
import com.remindme.repository.TextRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class TextServiceImpl implements TextService{
    TextRepository textRepository;
    FriendService friendService;
    OccasionService occasionService;
    @Override
    public void addText(Long occasionId, Text text) {
        //validation done by occasion service
        Occasion occasion=occasionService.getOccasionByOccasionId(occasionId);
        text.setOccasion(occasion);
        textRepository.save(text);
    }

    @Override
    public List<Text> getTexts(Long occasionId) {
        Occasion occasion=occasionService.getOccasionByOccasionId(occasionId);
        return occasion.getTexts();
    }

    @Override
    public List<Text> getTextsByUser(Long accountId) {
        List<Text> texts=new ArrayList<>();
        //Validation completed by friendService
        List<Friend> friends=friendService.getFriendsByUserId(accountId);

        for(Friend friend : friends){
            List<Occasion> occasions=occasionService.getOccasionByFriendId(friend.getId());
            for (Occasion occasion : occasions){
                List<Text> currentTexts=occasion.getTexts();
                texts.addAll(currentTexts);
            }
        }
        return texts;
    }
}
