package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import com.example.schooloperationsystem.rest.facade.StaffFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffFacade staffFacade;

    @GetMapping
    public ResponseEntity<List<StaffDetailsDto>> getAll() {
        log.info("Executing get all staff rest API");

        ResponseEntity<List<StaffDetailsDto>> responseEntity = ResponseEntity.ok(staffFacade.getAll());

        log.info("Successfully executed get all staff, response entity-{}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<StaffDetailsDto> create(@RequestBody CreateStaffRequestDto requestDto) {
        log.info("Executing create staff rest API, request-{}", requestDto);

        ResponseEntity<StaffDetailsDto> responseEntity = ResponseEntity.ok(staffFacade.create(requestDto));

        log.info("Successfully executed create staff, response entity-{}", responseEntity);
        return responseEntity;
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDetailsDto> deleteById(@PathVariable("id") Long id) {
        log.info("Executing delete staff by id rest API, id-{}", id);

        ResponseEntity<StaffDetailsDto> responseEntity = ResponseEntity.ok(staffFacade.deleteById(id));

        log.info("Successfully executed delete staff by id, response entity-{}", responseEntity);
        return responseEntity;
    }

    @GetMapping("schools/{schoolId}")
    public ResponseEntity<List<StaffDetailsDto>> getBySchoolId(@PathVariable("schoolId") Long schoolId) {
        log.info("Executing get staff by school id rest API, id-{}", schoolId);

        ResponseEntity<List<StaffDetailsDto>> responseEntity = ResponseEntity.ok(staffFacade.getBySchoolId(schoolId));

        log.info("Successfully executed get staff by school id, response entity-{}", responseEntity);
        return responseEntity;
    }
}