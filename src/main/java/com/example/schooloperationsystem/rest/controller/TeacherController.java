package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.TeacherDetailsDto;
import com.example.schooloperationsystem.rest.facade.TeacherFacade;
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

    private final TeacherFacade teacherFacade;

    @GetMapping
    public ResponseEntity<List<TeacherDetailsDto>> getAllTeachers() {
        return ResponseEntity.ok(teacherFacade.getAllTeachers());
    }

    @PostMapping
    public ResponseEntity<TeacherDetailsDto> createTeacher(@RequestBody CreateTeacherRequestDto requestDto) {
        return ResponseEntity.ok(teacherFacade.createTeacher(requestDto));
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<TeacherDetailsDto> getByStaffId(@PathVariable("staffId") Long staffId) {
        return ResponseEntity.ok(teacherFacade.getByStaffId(staffId));
    }
}
