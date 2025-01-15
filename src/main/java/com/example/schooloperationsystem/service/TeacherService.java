package com.example.schooloperationsystem.service;

import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.service.params.CreateTeacherParams;

import java.util.List;

public interface TeacherService {
    List<Teacher> getTeachers();

    Teacher addTeacher(CreateTeacherParams params);

    Teacher getTeacherById(Long id);
}
