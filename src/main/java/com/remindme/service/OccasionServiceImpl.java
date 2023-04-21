package com.remindme.service;

import com.remindme.entity.Friend;
import com.remindme.entity.Occasion;
import com.remindme.exception.BadInputException;
import com.remindme.exception.NotFoundException;
import com.remindme.repository.FriendRepository;
import com.remindme.repository.OccasionRepository;
import com.remindme.validation.Validation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@AllArgsConstructor
public class OccasionServiceImpl implements  OccasionService{
    FriendRepository friendRepository;
    Validation validation=new Validation();
    OccasionRepository occasionRepository;
    @Override
    public void addOccasion(Long friendId, Occasion occasion) {
        Friend friend=validation.checkFriend(friendId,friendRepository);
        occasion.setFriend(friend);
        occasionRepository.save(occasion);
    }

    @Override
    public void removeOccasion(Long occasionId) {
        occasionRepository.delete(getOccasionByOccasionId(occasionId));
    }

    @Override
    public Occasion getOccasionByOccasionId(Long occasionId) {
        return occasionRepository
                .findById(occasionId)
                .orElseThrow(
                        ()->{
                            return new NotFoundException(
                                    "Occasion not found"
                            );
                        }
                );
    }

    @Override
    public List<Occasion> getOccasionByFriendId(Long friendID) {
        Friend friend=validation.checkFriend(friendID,friendRepository);

        return friend.getOccasions();
    }

    @Override
    public void updateOccasion(Long occasionId, String occasionName, LocalDate occasionDate) {
        Occasion occasion=getOccasionByOccasionId(occasionId);
        if(!occasionName.equals("")){
            occasion.setOccasionName(occasionName);
        }
        if(!occasionDate.equals("")){
            occasion.setOccasionDate(occasionDate);
        }
        else {
            throw new BadInputException("No inputs for changing the occasion where provided");
        }
        occasionRepository.save(occasion);
    }

}
