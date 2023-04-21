package com.remindme.repository;

import com.remindme.entity.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccasionRepository extends JpaRepository<Occasion, Long> {
    @Query(value = "SELECT * FROM occasion WHERE MONTH(occasion_date) = :month AND DAY(occasion_date) = :day", nativeQuery = true)
    List<Occasion> findOccasionsByOccasionDate(@Param("month") int month, @Param("day") int day);

}
