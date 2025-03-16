package com.example.teachermanagement.repositories;

import com.example.teachermanagement.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByTeacherId(long teacherId);
}
