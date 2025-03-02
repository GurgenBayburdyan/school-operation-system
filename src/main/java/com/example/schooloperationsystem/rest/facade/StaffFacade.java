package com.example.schooloperationsystem.rest.facade;

import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StaffFacade {
    List<StaffDetailsDto> getAllStaff();

    StaffDetailsDto create(CreateStaffRequestDto requestDto);

    StaffDetailsDto delete(Long id);
}
