package com.example.teachermanagement.repositories;

import com.example.teachermanagement.model.TeacherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherReppository extends JpaRepository<TeacherEntity,Long> {
    Page<TeacherEntity> findAll(Pageable pageable);//phân trang
}
