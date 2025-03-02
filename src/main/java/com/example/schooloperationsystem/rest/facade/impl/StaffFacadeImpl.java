package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.mapper.StaffMapper;
import com.example.schooloperationsystem.rest.controller.validator.StaffValidator;
import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import com.example.schooloperationsystem.rest.facade.StaffFacade;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.params.CreateStaffParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class StaffFacadeImpl implements StaffFacade {
    private final StaffService service;
    private final StaffMapper mapper;
    private final StaffValidator staffValidator;
    private final StaffMapper staffMapper;

    @Override
    public List<StaffDetailsDto> getAllStaff() {
        log.info("Executing get all staff rest API");

        List<Staff> response = service.get();
        List<StaffDetailsDto> staffDetailsDtos = mapper.mapList(response);

        log.info("Successfully executed get staff rest API, response - {}", staffDetailsDtos);
        return staffDetailsDtos;
    }

    @Override
    public StaffDetailsDto create(CreateStaffRequestDto requestDto) {
        log.info("Executing create staff for the provided request to - {}:", requestDto);

        Optional<ErrorType> optionalErrorType = staffValidator.validateCreate(requestDto);

        if (optionalErrorType.isPresent()) {
            StaffDetailsDto staffDetailsDto = new StaffDetailsDto();
            staffDetailsDto.setErrorType(optionalErrorType.get());
            log.info("Executing create staff failed, error-{}", optionalErrorType.get());
            return staffDetailsDto;
        } else {
            CreateStaffParams params = new CreateStaffParams(
                    requestDto.getFirstName(),
                    requestDto.getLastName(),
                    requestDto.getDateOfBirth(),
                    requestDto.getSchoolId()
            );
            Staff response = service.add(params);
            StaffDetailsDto staffDetailsDto = mapper.mapToStaffDetailsDto(response);
            log.info("Successfully executed create staff rest API, response - {}", staffDetailsDto);
            return staffDetailsDto;
        }
    }

    @Override
    public StaffDetailsDto delete(Long id) {
        log.info("Executing delete staff by id-{}", id);

        Staff response = service.deleteById(id);

        if (response == null) {
            StaffDetailsDto staffDetailsDto = new StaffDetailsDto();
            staffDetailsDto.setErrorType(ErrorType.STAFF_NOT_FOUND);
            return staffDetailsDto;
        }


        StaffDetailsDto staffDetailsDto = staffMapper.mapToStaffDetailsDto(response);

        log.info("Successfully executed delete staff by id rest API, response - {}", staffDetailsDto);
        return staffDetailsDto;
    }
}
