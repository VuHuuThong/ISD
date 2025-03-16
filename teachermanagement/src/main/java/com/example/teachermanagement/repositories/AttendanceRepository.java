package com.example.teachermanagement.repositories;

import com.example.teachermanagement.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Attendance findByTeacherIdAndDate(Long teacherId, String date);

    List<Attendance> findByTeacherId(Long teacherId);
}
