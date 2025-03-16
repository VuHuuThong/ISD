package com.example.teachermanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Teacher teacher;
    private String checkInTime;
    private String checkOutTime;
    private String date;
}
