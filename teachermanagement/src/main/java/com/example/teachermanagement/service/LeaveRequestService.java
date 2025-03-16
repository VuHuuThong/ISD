package com.example.teachermanagement.service;

import com.example.teachermanagement.model.LeaveRequest;
import com.example.teachermanagement.repositories.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestService {
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public LeaveRequest createLeaveRequest(LeaveRequest request){
        return leaveRequestRepository.save(request);
    }

    public List<LeaveRequest> getLeaveRequestList(Long id){
        return leaveRequestRepository.findByTeacherId(id);
    }
}
