package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.controller.validator.SchoolClassValidator;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.mapper.SchoolClassMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.SchoolClassDetailsDto;
import com.example.schooloperationsystem.rest.dto.response.SchoolDetailsDto;
import com.example.schooloperationsystem.rest.facade.SchoolClassFacade;
import com.example.schooloperationsystem.rest.facade.SchoolFacade;
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
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolFacade schoolFacade;

    @GetMapping
    public ResponseEntity<List<SchoolDetailsDto>> getClasses() {
        return ResponseEntity.ok(schoolFacade.get());
    }

    @PostMapping
    public ResponseEntity<SchoolDetailsDto> createClass(@RequestBody CreateSchoolRequestDto requestDto) {
        return ResponseEntity.ok(schoolFacade.create(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolDetailsDto> getClassById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(schoolFacade.getById(id));
    }
}