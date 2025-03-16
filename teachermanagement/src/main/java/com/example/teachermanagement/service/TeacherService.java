package com.example.teachermanagement.service;

import com.example.teachermanagement.exceptions.DataNotFoundException;
import com.example.teachermanagement.model.*;
import com.example.teachermanagement.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements ITeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private TeachingScheduleRepository scheduleRepository;

    @Autowired
    private UpdateRequestRepository updateRequestRepository;

    @Override
    public Teacher createTeacher(Teacher teacher) {
       Teacher teacher1 = teacherRepository.save(teacher);
       return teacher1;
    }

    @Override
    public Teacher getTeacherById(long id) throws Exception {
         return teacherRepository.findById(id).orElseThrow(()-> new DataNotFoundException("Cannot find teacher" + id));
    }

    @Override
    public Teacher updateTeacher(long id, Teacher teacher) throws Exception {
        Teacher existingTeaccher = teacherRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot find teacher with id: " + id));
        if (existingTeaccher != null) {
            existingTeaccher.setAvailability(teacher.getAvailability());
            existingTeaccher.setUser(teacher.getUser());
            existingTeaccher.setFullName(teacher.getFullName());
            existingTeaccher.setHireDate(teacher.getHireDate());
            existingTeaccher.setPhoneNumber(teacher.getPhoneNumber());
            existingTeaccher.setProfileImage(teacher.getProfileImage());

            teacherRepository.save(teacher);
        }
        return existingTeaccher;
    }

    @Override
    public void deleteTeacher(long id) throws Exception {
        Optional<Teacher> optionalteacher = teacherRepository.findById(id);
        optionalteacher.ifPresent(teacherRepository::delete);
    }

    @Override
    public Page<Teacher> getAllTeachers(PageRequest pageRequest) {
        return teacherRepository.findAll(pageRequest);
    }

    public UpdateRequest submitUpdateRequest(UpdateRequest request) {
        return updateRequestRepository.save(request);
    }

    public LeaveRequest submitLeaveRequest(LeaveRequest request) {
        return leaveRequestRepository.save(request);
    }

    public List<LeaveRequest> getLeaveRequests(Long teacherId) {
        return leaveRequestRepository.findByTeacherId(teacherId);
    }

    public List<TeachingSchedule> getTeachingSchedule(Long teacherId) {
        return scheduleRepository.findByTeacherId(teacherId);
    }
    public Attendance logAttendance(Long teacherId, String type) {
        Attendance attendance = new Attendance();
        attendance.setTeacher(teacherRepository.getReferenceById(teacherId));
        attendance.setDate(LocalDateTime.now().toString().substring(0, 10));
        if ("check-in".equals(type)) {
            attendance.setCheckInTime(LocalDateTime.now().toString());
        } else if ("check-out".equals(type)) {
            Attendance today = attendanceRepository.findByTeacherIdAndDate(teacherId, attendance.getDate());
            today.setCheckOutTime(LocalDateTime.now().toString());
            return attendanceRepository.save(today);
        }
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceHistory(Long teacherId) {
        return attendanceRepository.findByTeacherId(teacherId);
    }
}
