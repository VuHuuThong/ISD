package com.example.teachermanagement.repositories;

import com.example.teachermanagement.model.UpdateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UpdateRequestRepository extends JpaRepository<UpdateRequest, Long> {
    List<UpdateRequest> findByTeacherId(Long teacherId);
}
