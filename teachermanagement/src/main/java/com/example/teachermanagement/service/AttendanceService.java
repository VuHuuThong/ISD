package com.example.teachermanagement.service;

import com.example.teachermanagement.model.Attendance;
import com.example.teachermanagement.repositories.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance findByTeacherIdAndDate(long teacherId, LocalDate date) {
        return attendanceRepository.findByTeacherIdAndDate(teacherId, date);
    }

    public Attendance handleSaveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> findByTeacherId(long id) {
        return attendanceRepository.findByTeacherId(id);
    }
}
