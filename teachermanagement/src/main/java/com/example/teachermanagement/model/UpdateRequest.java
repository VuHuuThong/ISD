package com.example.teachermanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "UpdateRequest")
public class UpdateRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Teacher teacher;
    private String fieldToUpdate;
    private String newValue;
    private String status = "Pending";
}
