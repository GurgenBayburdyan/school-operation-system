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
    public ResponseEntity<List<SchoolDetailsDto>> getAll() {
        log.info("Executing get all schools rest API");

        ResponseEntity<List<SchoolDetailsDto>> responseEntity = ResponseEntity.ok(schoolFacade.get());

        log.info("Successfully executed get all schools, response entity-{}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<SchoolDetailsDto> create(@RequestBody CreateSchoolRequestDto requestDto) {
        log.info("Executing create school rest API, request-{}", requestDto);

        ResponseEntity<SchoolDetailsDto> responseEntity = ResponseEntity.ok(schoolFacade.create(requestDto));

        log.info("Successfully executed create school, response entity-{}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolDetailsDto> getById(@PathVariable("id") Long id) {
        log.info("Executing get school by id rest API, id-{}", id);

        ResponseEntity<SchoolDetailsDto> responseEntity = ResponseEntity.ok(schoolFacade.getById(id));

        log.info("Successfully executed get school by id, response entity-{}", responseEntity);
        return responseEntity;
    }
}
