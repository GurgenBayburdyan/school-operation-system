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
    public ResponseEntity<List<TeacherDetailsDto>> getAll() {
        log.info("Executing get all teachers rest API");

        ResponseEntity<List<TeacherDetailsDto>> responseEntity = ResponseEntity.ok(teacherFacade.getAll());

        log.info("Successfully executed get all teachers, response entity-{}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<TeacherDetailsDto> create(@RequestBody CreateTeacherRequestDto requestDto) {
        log.info("Executing create teacher rest API, request-{}", requestDto);

        ResponseEntity<TeacherDetailsDto> responseEntity = ResponseEntity.ok(teacherFacade.create(requestDto));

        log.info("Successfully executed create teacher, response entity-{}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<TeacherDetailsDto> getByStaffId(@PathVariable("staffId") Long staffId) {
        log.info("Executing get teacher by staff id rest API, id-{}", staffId);

        ResponseEntity<TeacherDetailsDto> responseEntity = ResponseEntity.ok(teacherFacade.getByStaffId(staffId));

        log.info("Successfully executed get teacher by id, response entity-{}", responseEntity);
        return responseEntity;
    }
}
