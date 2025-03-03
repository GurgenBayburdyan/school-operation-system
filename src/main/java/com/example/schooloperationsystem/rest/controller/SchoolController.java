package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.dto.request.CreateSchoolRequestDto;
import com.example.schooloperationsystem.rest.dto.response.SchoolDetailsDto;
import com.example.schooloperationsystem.rest.facade.SchoolFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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