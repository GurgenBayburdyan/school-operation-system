package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.mapper.StaffMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.params.CreateStaffParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/staff")
public class StaffController {

    private static final Logger logger = LoggerFactory.getLogger(StaffController.class);

    private final StaffService service;
    private final StaffMapper mapper;

    @GetMapping
    public List<StaffDetailsDto> getAllStaff() {
        logger.info("Executing get all staff rest API");

        List<Staff> response = service.getStaff();

        logger.info("Successfully executed get staff rest API, response entity - {}", response);
        return mapper.mapList(response);
    }

    @PostMapping
    public StaffDetailsDto create(@RequestBody CreateStaffRequestDto requestDto) {
        logger.info("Executing create staff for the provided request to - {}:", requestDto);

        CreateStaffParams params = new CreateStaffParams();
        params.setFirstName(requestDto.getFirstName());
        params.setLastName(requestDto.getLastName());
        params.setDateOfBirth(requestDto.getDateOfBirth());
        Staff response = service.addStaff(params);

        logger.info("Successfully executed create staff rest API, response entity - {}", response);
        return mapper.map(response);
    }
}
