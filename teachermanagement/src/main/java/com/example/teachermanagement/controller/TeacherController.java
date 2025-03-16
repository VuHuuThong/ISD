package com.example.teachermanagement.controller;

import com.example.teachermanagement.model.*;
import com.example.teachermanagement.responses.TeacherListResponse;
import com.example.teachermanagement.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UpdateRequestService updateRequestService;

    @Autowired
    private TeachingScheduleService teachingScheduleService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private LeaveRequestService leaveRequestService;
    @PostMapping("")
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody Teacher teacher,
            BindingResult result
    ) {
        try {
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            Teacher teacher1 = teacherService.createTeacher(teacher);
            return ResponseEntity.ok(teacher1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<TeacherListResponse> getTeachers(
            @RequestParam("page")     int page,
            @RequestParam("limit")    int limit
    ) {
        // Tạo Pageable từ thông tin trang và giới hạn
        PageRequest pageRequest = PageRequest.of(
                page, limit,
                Sort.by("createdAt").descending());
        Page<Teacher> teacherpage = teacherService.getAllTeachers(pageRequest);
        // Lấy tổng số trang
        int totalPages = teacherpage.getTotalPages();
        List<Teacher> teacherss = teacherpage.getContent();
        return ResponseEntity.ok(TeacherListResponse.builder()
                .totalPages(totalPages)
                .teacherList(teacherss)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(
            @PathVariable("id") long teacherId
    ) {
        try {
            Teacher existingTeacher = teacherService.getTeacherById(teacherId);
            return ResponseEntity.ok(existingTeacher);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable long id) {
        try {
            teacherService.deleteTeacher(id);
            return ResponseEntity.ok(String.format("Product with id = %d deleted successfully", id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(
            @PathVariable long id,
            @RequestBody Teacher teacher) {
        try {
            Teacher updatedTeacher = teacherService.updateTeacher(id, teacher);
            return ResponseEntity.ok(updatedTeacher);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/update-requests")
    public UpdateRequest submitUpdateRequest(@RequestBody UpdateRequest request) {
        request.setStatus("PENDING");
        return updateRequestService.handeUpdateRequest(request);
    }

    @PostMapping("/leave-requests")
    public LeaveRequest submitLeaveRequest(@RequestBody LeaveRequest request) {
        request.setStatus("PENDING");
        return leaveRequestService.createLeaveRequest(request);
    }

    @GetMapping("/leave-requests")
    public List<LeaveRequest> getLeaveRequests(@RequestParam long teacherId) {
        return leaveRequestService.getLeaveRequestList(teacherId);
    }

    @GetMapping("/schedules")
    public List<TeachingSchedule> getSchedules(@RequestParam long teacherId) {
        return teachingScheduleService.getAllTeachingScheduleByTeacher(teacherId);
    }

    @PostMapping("/attendances/check-in")
    public Attendance checkIn(@RequestParam long teacherId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws Exception {
        Attendance attendance = attendanceService.findByTeacherIdAndDate(teacherId, date);
        if (attendance == null) {
            attendance = new Attendance();
            attendance.setTeacher(teacherService.getTeacherById(teacherId));
            attendance.setDate(date);
            attendance.setCheckInTime(LocalTime.now());
        } else if (attendance.getCheckInTime() != null) {
            throw new RuntimeException("Already checked in");
        } else {
            attendance.setCheckInTime(LocalTime.now());
        }
        return attendanceService.handleSaveAttendance(attendance);
    }

    @PostMapping("/attendances/check-out")
    public Attendance checkOut(@RequestParam long teacherId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Attendance attendance = attendanceService.findByTeacherIdAndDate(teacherId, date);
        if (attendance == null || attendance.getCheckInTime() == null) {
            throw new RuntimeException("Must check in first");
        }
        if (attendance.getCheckOutTime() != null) {
            throw new RuntimeException("Already checked out");
        }
        attendance.setCheckOutTime(LocalTime.now());
        return attendanceService.handleSaveAttendance(attendance);
    }

    @GetMapping("/attendances")
    public List<Attendance> getAttendanceHistory(@RequestParam long teacherId) {
        return attendanceService.findByTeacherId(teacherId);
    }

}
