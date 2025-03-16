package com.example.teachermanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "TeachingSchedule")
public class TeachingSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Teacher teacher;
    private String subject;
    private String className;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
}
