package com.example.teachermanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PerformanceEvaluation")
public class PerformanceEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Teacher teacher;
    private String attendanceScore;
    private String studentFeedback;
    private String supervisorComments;
}
