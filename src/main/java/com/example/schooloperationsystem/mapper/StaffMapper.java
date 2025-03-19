package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import com.example.schooloperationsystem.service.params.CreateStaffParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class StaffMapper {

    public StaffDetailsDto map(Staff staff) {
        log.trace("Mapping staff - {} to staff details dto", staff);

        StaffDetailsDto staffDetailsDto = new StaffDetailsDto();
        staffDetailsDto.setId(staff.getId());
        staffDetailsDto.setFirstName(staff.getFirstName());
        staffDetailsDto.setLastName(staff.getLastName());
        staffDetailsDto.setDateOfBirth(staff.getDateOfBirth());
        staffDetailsDto.setSchoolId(staff.getSchool().getId());

        log.trace("Mapped staff - {}", staffDetailsDto);
        return staffDetailsDto;
    }

    public List<StaffDetailsDto> mapList(List<Staff> staffs) {
        List<StaffDetailsDto> staffDetailsDtos = new ArrayList<>();
        for (Staff staff : staffs) {
            staffDetailsDtos.add(map(staff));
        }
        return staffDetailsDtos;
    }

    public CreateStaffParams fromRequestDtoToParams(CreateStaffRequestDto requestDto) {
        return new CreateStaffParams(requestDto.getFirstName(), requestDto.getLastName(), requestDto.getDateOfBirth(), requestDto.getSchoolId());
    }
}