package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StaffMapper {
    public StaffDetailsDto map(Staff staff) {
        StaffDetailsDto staffDetailsDto = new StaffDetailsDto();
        staffDetailsDto.setFirstName(staff.getFirstName());
        staffDetailsDto.setLastName(staff.getLastName());
        staffDetailsDto.setDateOfBirth(staff.getDateOfBirth());
        return staffDetailsDto;
    }

    public List<StaffDetailsDto> mapList(List<Staff> staffs) {
        List<StaffDetailsDto> staffDetailsDtos = new ArrayList<>();
        for (Staff staff : staffs) {
            staffDetailsDtos.add(map(staff));
        }
        return staffDetailsDtos;
    }
}
