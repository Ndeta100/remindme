package com.remindme.repository;

import com.remindme.entity.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccasionRepository extends JpaRepository<Occasion, Long> {
}
