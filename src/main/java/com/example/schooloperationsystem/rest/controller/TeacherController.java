package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.mapper.TeacherMapper;
import com.example.schooloperationsystem.rest.dto.StaffDto;
import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.TeacherDetailsDto;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateTeacherParams;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;
    private final TeacherMapper mapper;
    private final StaffService staffService;

    public TeacherController(TeacherService service, TeacherMapper mapper, StaffService staffService) {
        this.service = service;
        this.mapper = mapper;
        this.staffService = staffService;
    }

    @GetMapping
    public List<TeacherDetailsDto> getAllTeachers() {
        List<Teacher> response = service.getTeachers();
        return mapper.mapList(response);
    }

    @PostMapping
    public TeacherDetailsDto createTeacher(@RequestBody CreateTeacherRequestDto requestDto) {
        CreateTeacherParams params=new CreateTeacherParams();
        Staff staff=staffService.getStaffById(requestDto.getStaffId());
        StaffDto staffDto=new StaffDto();
        staffDto.setFirstName(staff.getFirstName());
        staffDto.setLastName(staff.getLastName());
        staff.setDateOfBirth(staff.getDateOfBirth());

        params.setStaffDto(staffDto);
        Teacher response = service.addTeacher(params);
        return mapper.map(response);
    }
}
