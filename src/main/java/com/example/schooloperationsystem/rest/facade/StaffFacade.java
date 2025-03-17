package com.example.schooloperationsystem.rest.facade;

import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import java.util.List;

public interface StaffFacade {
    List<StaffDetailsDto> getAll();

    StaffDetailsDto create(CreateStaffRequestDto requestDto);

    StaffDetailsDto delete(Long id);

    List<StaffDetailsDto> getBySchoolId(Long schoolId);
}
