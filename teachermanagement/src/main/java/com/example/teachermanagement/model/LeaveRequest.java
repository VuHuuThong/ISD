package com.example.teachermanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "LeaveRequest")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Teacher teacher;
    private String leaveType;
    private String startDate;
    private String endDate;
    private String reason;
    private String status = "Pending";
}
