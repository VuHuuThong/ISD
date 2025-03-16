package com.example.teachermanagement.repositories;

import com.example.teachermanagement.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByTeacherId(long teacherId);
    Attendance findByTeacherIdAndDate(long teacherId, LocalDate date);
    List<Attendance> findByDateBetween(LocalDate start, LocalDate end);
}
