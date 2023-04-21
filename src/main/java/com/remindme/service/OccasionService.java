package com.remindme.service;

import com.remindme.entity.Occasion;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public interface OccasionService {
    public void addOccasion(Long friendId, Occasion occasion);
    public void removeOccasion(Long occasionId);
    public  Occasion getOccasionByOccasionId(Long occasionId);
    public List<Occasion>getOccasionByFriendId(Long friendID);
    @Transactional
    public void updateOccasion(Long occasionId, String occasionName, LocalDate occasionDate);
}
