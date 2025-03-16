package com.example.teachermanagement.service;

import com.example.teachermanagement.model.UpdateRequest;
import com.example.teachermanagement.repositories.UpdateRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateRequestService {
    @Autowired
    private UpdateRequestRepository updateRequestRepository;

    public UpdateRequest handeUpdateRequest(UpdateRequest updateRequest) {
        return updateRequestRepository.save(updateRequest);
    }
}
