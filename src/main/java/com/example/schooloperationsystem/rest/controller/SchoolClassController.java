package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.controller.validator.SchoolClassValidator;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.mapper.SchoolClassMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.SchoolClassDetailsDto;
import com.example.schooloperationsystem.rest.facade.SchoolClassFacade;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.params.CreateSchoolClassParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/classes")
public class SchoolClassController {

    private final SchoolClassFacade schoolClassFacade;

    @GetMapping
    public ResponseEntity<List<SchoolClassDetailsDto>> getClasses() {
        return ResponseEntity.ok(schoolClassFacade.getClasses());
    }

    @PostMapping
    public ResponseEntity<SchoolClassDetailsDto> createClass(@RequestBody CreateSchoolClassRequestDto requestDto) {
        return ResponseEntity.ok(schoolClassFacade.createClass(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolClassDetailsDto> getClassById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(schoolClassFacade.getClassById(id));
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<SchoolClassDetailsDto>> getClassesBySchoolId(@PathVariable("schoolId") Long schoolId) {
        return ResponseEntity.ok(schoolClassFacade.getClassesBySchoolId(schoolId));
    }
}