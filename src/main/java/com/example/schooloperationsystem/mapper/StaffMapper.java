package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.rest.dto.StaffDto;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class StaffMapper {

    public StaffDetailsDto mapToStaffDetailsDto(Staff staff) {
        log.trace("Mapping staff - {} to staff details dto", staff);

        StaffDetailsDto staffDetailsDto = new StaffDetailsDto();
        staffDetailsDto.setFirstName(staff.getFirstName());
        staffDetailsDto.setLastName(staff.getLastName());
        staffDetailsDto.setDateOfBirth(staff.getDateOfBirth());

        log.trace("Mapped staff - {}", staffDetailsDto);
        return staffDetailsDto;
    }

    public List<StaffDetailsDto> mapList(List<Staff> staffs) {
        List<StaffDetailsDto> staffDetailsDtos = new ArrayList<>();
        for (Staff staff : staffs) {
            staffDetailsDtos.add(mapToStaffDetailsDto(staff));
        }
        return staffDetailsDtos;
    }

    public StaffDto mapToStaffDto(Staff staff) {
        StaffDto staffDto = new StaffDto();
        staffDto.setFirstName(staff.getFirstName());
        staffDto.setLastName(staff.getLastName());
        staffDto.setDateOfBirth(staff.getDateOfBirth());
        return staffDto;
    }
}