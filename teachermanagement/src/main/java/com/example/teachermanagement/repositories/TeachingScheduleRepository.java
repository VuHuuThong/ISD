package com.example.teachermanagement.repositories;

import com.example.teachermanagement.model.TeachingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachingScheduleRepository extends JpaRepository<TeachingSchedule, Long> {
    List<TeachingSchedule> findByTeacherId(Long teacherId);
}
