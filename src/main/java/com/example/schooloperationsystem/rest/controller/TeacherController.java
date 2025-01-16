package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.mapper.TeacherMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.TeacherDetailsDto;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateTeacherParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    private final TeacherService service;
    private final TeacherMapper mapper;

    @GetMapping
    public ResponseEntity<List<TeacherDetailsDto>> getAllTeachers() {
        logger.info("Executing get all teachers rest API");

        List<Teacher> response = service.getTeachers();

        logger.info("Successfully executed get teachers rest API, response entity - {}", response);
        return ResponseEntity.ok(mapper.mapList(response));
    }

    @PostMapping
    public ResponseEntity<TeacherDetailsDto> createTeacher(@RequestBody CreateTeacherRequestDto requestDto) {
        logger.info("Executing create teacher for the provided request to - {}:", requestDto);

        CreateTeacherParams params = new CreateTeacherParams();
        params.setStaffId(requestDto.getStaffId());
        Teacher response = service.create(params);

        logger.info("Successfully executed create teacher rest API, response entity - {}", response);
        return ResponseEntity.ok(mapper.map(response));
    }
}
