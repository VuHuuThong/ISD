package com.example.teachermanagement.service;

import com.example.teachermanagement.model.TeacherEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface ITeacherService {
    TeacherEntity createTeacher(TeacherEntity teacherEntity);
    TeacherEntity getTeacherById(long id) throws Exception;
    TeacherEntity updateTeacher(long id, TeacherEntity teacherEntity) throws Exception;
    void deleteTeacher(long id) throws Exception;
    Page<TeacherEntity> getAllTeachers(PageRequest pageable);
}
