package com.example.teachermanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teachers")
@Builder
public class TeacherEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "fullname", nullable = false)
    private String fullName;
    @Column(name = "phoneNumber" , nullable = false)
    private String phoneNumber;

    @Column(name="qualifications",nullable = false)
    private String qualifications;
    @Column(name="availability" , nullable = false)
    private String availability;

    @Column(name="hireDate",nullable = false)
    private LocalDate hireDate;
    @Column(name="profileImage")
    private String profileImage; // Đường dẫn ảnh đại diện
}

