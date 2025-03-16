package com.example.teachermanagement.service;

import com.example.teachermanagement.model.Teacher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface ITeacherService {
    Teacher createTeacher(Teacher teacher);
    Teacher getTeacherById(long id) throws Exception;
    Teacher updateTeacher(long id, Teacher teacher) throws Exception;
    void deleteTeacher(long id) throws Exception;
    Page<Teacher> getAllTeachers(PageRequest pageable);
}
