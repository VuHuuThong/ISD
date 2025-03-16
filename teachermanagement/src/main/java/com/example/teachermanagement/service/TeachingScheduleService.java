package com.example.teachermanagement.service;

import com.example.teachermanagement.model.TeachingSchedule;
import com.example.teachermanagement.repositories.TeachingScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachingScheduleService {
    @Autowired
    private TeachingScheduleRepository teachingScheduleRepository;

    public List<TeachingSchedule> getAllTeachingScheduleByTeacher(Long teacherId) {
        return teachingScheduleRepository.findByTeacherId(teacherId);
    }
}
