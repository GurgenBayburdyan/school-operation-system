package com.example.schooloperationsystem.rest.facade;

import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.TeacherDetailsDto;

import java.util.List;

public interface TeacherFacade {
    List<TeacherDetailsDto> getAllTeachers();

    TeacherDetailsDto createTeacher(CreateTeacherRequestDto requestDto);

    TeacherDetailsDto getByStaffId(Long staffId);
}
