package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.SchoolClassDetailsDto;
import com.example.schooloperationsystem.rest.facade.SchoolClassFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/classes")
public class SchoolClassController {

    private final SchoolClassFacade schoolClassFacade;

    @GetMapping
    public ResponseEntity<List<SchoolClassDetailsDto>> getAll() {
        log.info("Executing get all classes rest API");

        ResponseEntity<List<SchoolClassDetailsDto>> responseEntity = ResponseEntity.ok(schoolClassFacade.getClasses());

        log.info("Successfully executed get all classes, response entity-{}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<SchoolClassDetailsDto> create(@RequestBody CreateSchoolClassRequestDto requestDto) {
        log.info("Executing create class rest API, request-{}", requestDto);

        ResponseEntity<SchoolClassDetailsDto> responseEntity = ResponseEntity.ok(schoolClassFacade.createClass(requestDto));

        log.info("Successfully executed create classes, response entity-{}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolClassDetailsDto> getById(@PathVariable("id") Long id) {
        log.info("Executing get class by id rest API, id-{}", id);

        ResponseEntity<SchoolClassDetailsDto> responseEntity = ResponseEntity.ok(schoolClassFacade.getClassById(id));

        log.info("Successfully executed get class by id, response entity-{}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<SchoolClassDetailsDto>> getBySchoolId(@PathVariable("schoolId") Long schoolId) {
        log.info("Executing get classes by school id rest API, id-{}", schoolId);

        ResponseEntity<List<SchoolClassDetailsDto>> responseEntity = ResponseEntity.ok(schoolClassFacade.getClassesBySchoolId(schoolId));

        log.info("Successfully executed get classes by school id, response entity-{}", responseEntity);
        return responseEntity;
    }
}