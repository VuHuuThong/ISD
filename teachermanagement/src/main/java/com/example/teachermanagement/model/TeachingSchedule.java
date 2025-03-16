package com.example.teachermanagement.model;

import jakarta.persistence.*;
import lombok.Data;

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
    private String timeSlot;
    private String classroomLocation;
}
