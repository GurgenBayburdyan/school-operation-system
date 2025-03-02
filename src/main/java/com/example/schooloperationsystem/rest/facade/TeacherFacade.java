package com.example.schooloperationsystem.rest.facade;

import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.TeacherDetailsDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TeacherFacade {
    List<TeacherDetailsDto> getAllTeachers();

    TeacherDetailsDto createTeacher(CreateTeacherRequestDto requestDto);

    TeacherDetailsDto getByStaffId(Long staffId);
}
