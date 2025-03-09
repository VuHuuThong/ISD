package com.example.teachermanagement.responses;

import com.example.teachermanagement.model.TeacherEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class TeacherListResponse {
    private List<TeacherEntity> teacherEntityList;
    private int totalPages;
}
