package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.mapper.StaffMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.params.CreateStaffParams;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService service;
    private final StaffMapper mapper;

    public StaffController(StaffService service, StaffMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<StaffDetailsDto> getAllStaff() {
        List<Staff> staffList = service.getStaff();
        return mapper.mapList(staffList);
    }

    @PostMapping
    public StaffDetailsDto createStaff(@RequestBody CreateStaffRequestDto requestDto) {
        CreateStaffParams params = new CreateStaffParams();
        params.setFirstName(requestDto.getFirstName());
        params.setLastName(params.getLastName());
        params.setDateOfBirth(params.getDateOfBirth());
        Staff staff = service.addStaff(params);
        return mapper.map(staff);
    }
}
