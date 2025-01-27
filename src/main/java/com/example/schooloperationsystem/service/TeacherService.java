package com.example.schooloperationsystem.service;

import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.service.params.CreateTeacherParams;

import java.util.List;

public interface TeacherService {
    List<Teacher> get();

    Teacher create(CreateTeacherParams params);

    Teacher getById(Long id);

    boolean existsById(Long id);
}
