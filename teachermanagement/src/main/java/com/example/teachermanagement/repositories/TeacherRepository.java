package com.example.teachermanagement.repositories;

import com.example.teachermanagement.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Page<Teacher> findAll(Pageable pageable);//phân trang
}
