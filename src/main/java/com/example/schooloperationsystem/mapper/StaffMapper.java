package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class StaffMapper {

    private static final Logger logger = LoggerFactory.getLogger(StaffMapper.class);

    public StaffDetailsDto map(Staff staff) {
        logger.trace("Mapping staff - {} to staff details dto", staff);

        StaffDetailsDto staffDetailsDto = new StaffDetailsDto();
        staffDetailsDto.setFirstName(staff.getFirstName());
        staffDetailsDto.setLastName(staff.getLastName());
        staffDetailsDto.setDateOfBirth(staff.getDateOfBirth());

        logger.trace("Mapped staff - {}", staffDetailsDto);
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
