package com.remindme.repository;

import com.remindme.entity.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccasionRepository extends JpaRepository<Occasion, Long> {
    List<Occasion> findOccasionsByDate(int month, int day);
    Occasion findBirthdayByFriend(Long friendId);
}
