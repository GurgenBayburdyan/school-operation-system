package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.mapper.StaffMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.params.CreateStaffParams;
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

    private final StaffService service;
    private final StaffMapper mapper;

    @GetMapping
    public ResponseEntity<List<StaffDetailsDto>> getAllStaff() {
        log.info("Executing get all staff rest API");

        List<Staff> response = service.getStaff();
        ResponseEntity<List<StaffDetailsDto>> responseEntity = ResponseEntity.ok(mapper.mapList(response));

        log.info("Successfully executed get staff rest API, response entity - {}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<StaffDetailsDto> create(@RequestBody CreateStaffRequestDto requestDto) {
        log.info("Executing create staff for the provided request to - {}:", requestDto);

        CreateStaffParams params = new CreateStaffParams(
                requestDto.getFirstName(),
                requestDto.getLastName(),
                requestDto.getDateOfBirth()
        );

        Staff response = service.addStaff(params);
        ResponseEntity<StaffDetailsDto> responseEntity = ResponseEntity.ok(mapper.mapToStaffDetailsDto(response));

        log.info("Successfully executed create staff rest API, response entity - {}", responseEntity);
        return responseEntity;
    }
}