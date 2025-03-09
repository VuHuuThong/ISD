package com.example.teachermanagement.controller;

import com.example.teachermanagement.model.TeacherEntity;
import com.example.teachermanagement.responses.TeacherListResponse;
import com.example.teachermanagement.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
            @Valid @RequestBody TeacherEntity teacherEntity,
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
            TeacherEntity teacherEntity1 = teacherService.createTeacher(teacherEntity);
            return ResponseEntity.ok(teacherEntity1);
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
        Page<TeacherEntity> teacherpage = teacherService.getAllTeachers(pageRequest);
        // Lấy tổng số trang
        int totalPages = teacherpage.getTotalPages();
        List<TeacherEntity> teacherss = teacherpage.getContent();
        return ResponseEntity.ok(TeacherListResponse.builder()
                .totalPages(totalPages)
                .teacherEntityList(teacherss)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(
            @PathVariable("id") Long teacherId
    ) {
        try {
            TeacherEntity existingTeacher = teacherService.getTeacherById(teacherId);
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
            @RequestBody TeacherEntity teacherEntity) {
        try {
            TeacherEntity updatedTeacher = teacherService.updateTeacher(id, teacherEntity);
            return ResponseEntity.ok(updatedTeacher);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
