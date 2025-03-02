package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.rest.controller.validator.TeacherValidator;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.mapper.TeacherMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilInClassDetailsDto;
import com.example.schooloperationsystem.rest.dto.response.TeacherDetailsDto;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateTeacherParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;
    private final TeacherMapper mapper;
    private final TeacherValidator teacherValidator;

    @GetMapping
    public ResponseEntity<List<TeacherDetailsDto>> getAllTeachers() {
        log.info("Executing get all teachers rest API");

        List<Teacher> response = service.get();

        ResponseEntity<List<TeacherDetailsDto>> responseEntity = ResponseEntity.ok(mapper.mapList(response));
        log.info("Successfully executed get teachers rest API, response entity - {}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<TeacherDetailsDto> createTeacher(@RequestBody CreateTeacherRequestDto requestDto) {
        log.info("Executing create teacher for the provided request to - {}:", requestDto);

        Optional<ErrorType> optionalErrorType = teacherValidator.validateCreate(requestDto);

        if (optionalErrorType.isPresent()) {
            TeacherDetailsDto teacherDetailsDto = new TeacherDetailsDto();
            teacherDetailsDto.setErrorType(optionalErrorType.get());
            log.info("Executing create teacher failed, error-{}", optionalErrorType.get());
            return ResponseEntity.ok(teacherDetailsDto);
        } else {
            CreateTeacherParams params = new CreateTeacherParams(
                    requestDto.getStaffId()
            );
            Teacher response = service.create(params);
            ResponseEntity<TeacherDetailsDto> responseEntity = ResponseEntity.ok(mapper.mapToTeacherDetailsDto(response));
            log.info("Successfully executed create teacher rest API, response entity - {}", responseEntity);
            return responseEntity;
        }
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<TeacherDetailsDto> getByStaffId(@PathVariable("staffId") Long staffId) {
        log.info("Executing get teacher by staff id-{}", staffId);

        Teacher response = service.getByStaffId(staffId);

        if (response == null) {
            TeacherDetailsDto teacherDetailsDto=new TeacherDetailsDto();
            teacherDetailsDto.setErrorType(ErrorType.TEACHER_NOT_FOUND);
            return ResponseEntity.ok(teacherDetailsDto);
        }

        TeacherDetailsDto teacherDetailsDto = mapper.mapToTeacherDetailsDto(response);
        ResponseEntity<TeacherDetailsDto> responseEntity = ResponseEntity.ok(teacherDetailsDto);

        log.info("Successfully executed get teacher by staff id rest API, response entity - {}", responseEntity);
        return responseEntity;
    }
}
