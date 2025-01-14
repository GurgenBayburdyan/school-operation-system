package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.mapper.TeacherMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.TeacherDetailsDto;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateTeacherParams;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;
    private final TeacherMapper mapper;

    public TeacherController(TeacherService service, TeacherMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<TeacherDetailsDto> getAllTeachers() {
        List<Teacher> teacherList = service.getTeachers();
        return mapper.mapList(teacherList);
    }

    @PostMapping
    public TeacherDetailsDto createTeacher(@RequestBody CreateTeacherRequestDto requestDto) {
        CreateTeacherParams params=new CreateTeacherParams();
        params.setStaffId(requestDto.getStaffId());
        Teacher teacher = service.addTeacher(params);
        return mapper.map(teacher);
    }
}
