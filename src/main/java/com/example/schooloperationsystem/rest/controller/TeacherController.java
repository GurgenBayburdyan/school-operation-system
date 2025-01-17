package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.mapper.TeacherMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.TeacherDetailsDto;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateTeacherParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;
    private final TeacherMapper mapper;

    @GetMapping
    public ResponseEntity<List<TeacherDetailsDto>> getAllTeachers() {
        log.info("Executing get all teachers rest API");

        List<Teacher> response = service.getTeachers();

        log.info("Successfully executed get teachers rest API, response entity - {}", response);
        return ResponseEntity.ok(mapper.mapList(response));
    }

    @PostMapping
    public ResponseEntity<TeacherDetailsDto> createTeacher(@RequestBody CreateTeacherRequestDto requestDto) {
        log.info("Executing create teacher for the provided request to - {}:", requestDto);

        CreateTeacherParams params = new CreateTeacherParams(
                requestDto.getStaffId()
        );

        Teacher response = service.create(params);
        ResponseEntity<TeacherDetailsDto> responseEntity = ResponseEntity.ok(mapper.mapToTeacherDetailsDto(response));
        log.info("Successfully executed create teacher rest API, response entity - {}", responseEntity);
        return responseEntity;
    }
}