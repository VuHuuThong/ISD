package com.example.teachermanagement.controller;

import com.example.teachermanagement.model.*;
import com.example.teachermanagement.responses.TeacherListResponse;
import com.example.teachermanagement.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
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
            @PathVariable("id") Long teacherId
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
    public ResponseEntity<UpdateRequest> submitUpdateRequest(@RequestBody UpdateRequest request) {
        return ResponseEntity.ok(teacherService.submitUpdateRequest(request));
    }

    @PostMapping("/leave-requests")
    public ResponseEntity<LeaveRequest> submitLeaveRequest(@RequestBody LeaveRequest request) {
        return ResponseEntity.ok(teacherService.submitLeaveRequest(request));
    }

    @GetMapping("/leave-requests/{teacherId}")
    public ResponseEntity<List<LeaveRequest>> getLeaveRequests(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teacherService.getLeaveRequests(teacherId));
    }

    @GetMapping("/schedules/{teacherId}")
    public ResponseEntity<List<TeachingSchedule>> getTeachingSchedule(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teacherService.getTeachingSchedule(teacherId));
    }

    @PostMapping("/attendance/{teacherId}/{type}")
    public ResponseEntity<Attendance> logAttendance(@PathVariable Long teacherId, @PathVariable String type) {
        return ResponseEntity.ok(teacherService.logAttendance(teacherId, type));
    }

    @GetMapping("/attendance/{teacherId}")
    public ResponseEntity<List<Attendance>> getAttendanceHistory(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teacherService.getAttendanceHistory(teacherId));
    }
}
