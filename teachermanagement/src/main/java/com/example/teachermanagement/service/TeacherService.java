package com.example.teachermanagement.service;

import com.example.teachermanagement.exceptions.DataNotFoundException;
import com.example.teachermanagement.model.TeacherEntity;
import com.example.teachermanagement.repositories.TeacherReppository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService implements ITeacherService {
    @Autowired
    private TeacherReppository teacherReppository;
    @Override
    public TeacherEntity createTeacher(TeacherEntity teacherEntity) {
       TeacherEntity teacherEntity1 = teacherReppository.save(teacherEntity);
       return teacherEntity1;
    }

    @Override
    public TeacherEntity getTeacherById(long id) throws Exception {
         return teacherReppository.findById(id).orElseThrow(()-> new DataNotFoundException("Cannot find teacher" + id));
    }

    @Override
    public TeacherEntity updateTeacher(long id, TeacherEntity teacherEntity) throws Exception {
        TeacherEntity existingTeaccher = teacherReppository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot find teacher with id: " + id));
        if (existingTeaccher != null) {
            existingTeaccher.setAvailability(teacherEntity.getAvailability());
            existingTeaccher.setUser(teacherEntity.getUser());
            existingTeaccher.setFullName(teacherEntity.getFullName());
            existingTeaccher.setHireDate(teacherEntity.getHireDate());
            existingTeaccher.setPhoneNumber(teacherEntity.getPhoneNumber());
            existingTeaccher.setProfileImage(teacherEntity.getProfileImage());

            teacherReppository.save(teacherEntity);
        }
        return existingTeaccher;
    }

    @Override
    public void deleteTeacher(long id) throws Exception {
        Optional<TeacherEntity> optionalteacher = teacherReppository.findById(id);
        optionalteacher.ifPresent(teacherReppository::delete);
    }

    @Override
    public Page<TeacherEntity> getAllTeachers(PageRequest pageRequest) {
        return teacherReppository.findAll(pageRequest);
    }
}
