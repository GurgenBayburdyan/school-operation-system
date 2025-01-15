package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.mapper.StaffMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.params.CreateStaffParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private static final Logger logger = LoggerFactory.getLogger(HeadMasterController.class);

    private final StaffService service;
    private final StaffMapper mapper;

    public StaffController(StaffService service, StaffMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<StaffDetailsDto> getAllStaff() {
        logger.info("Getting Staff...");
        List<Staff> response = service.getStaff();
        logger.info("Got Staff {}", response);
        return mapper.mapList(response);
    }

    @PostMapping
    public StaffDetailsDto createStaff(@RequestBody CreateStaffRequestDto requestDto) {
        logger.info("Creating Staff with firstName: {}, lastName: {}, dateOfBirth: {}",
                requestDto.getFirstName(), requestDto.getLastName(), requestDto.getDateOfBirth());
        CreateStaffParams params = new CreateStaffParams();
        params.setFirstName(requestDto.getFirstName());
        params.setLastName(requestDto.getLastName());
        params.setDateOfBirth(requestDto.getDateOfBirth());
        Staff response = service.addStaff(params);
        logger.info("Created Staff {}", requestDto);
        return mapper.map(response);
    }
}
