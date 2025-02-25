package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.controller.validator.StaffValidator;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.mapper.StaffMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.params.CreateStaffParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService service;
    private final StaffMapper mapper;
    private final StaffValidator staffValidator;

    @GetMapping
    public ResponseEntity<List<StaffDetailsDto>> getAllStaff() {
        log.info("Executing get all staff rest API");

        List<Staff> response = service.get();
        ResponseEntity<List<StaffDetailsDto>> responseEntity = ResponseEntity.ok(mapper.mapList(response));

        log.info("Successfully executed get staff rest API, response entity - {}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<StaffDetailsDto> create(@RequestBody CreateStaffRequestDto requestDto) {
        log.info("Executing create staff for the provided request to - {}:", requestDto);

        Optional<ErrorType> optionalErrorType = staffValidator.validateCreate(requestDto);

        if (optionalErrorType.isPresent()) {
            StaffDetailsDto staffDetailsDto = new StaffDetailsDto();
            staffDetailsDto.setErrorType(optionalErrorType.get());
            log.info("Executing create staff failed, error-{}", optionalErrorType.get());
            return ResponseEntity.ok(staffDetailsDto);
        } else {
            CreateStaffParams params = new CreateStaffParams(
                    requestDto.getFirstName(),
                    requestDto.getLastName(),
                    requestDto.getDateOfBirth()
            );
            Staff response = service.add(params);
            ResponseEntity<StaffDetailsDto> responseEntity = ResponseEntity.ok(mapper.mapToStaffDetailsDto(response));
            log.info("Successfully executed create staff rest API, response entity - {}", responseEntity);
            return responseEntity;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StaffDetailsDto> delete(@PathVariable("id") Long id) {
        log.info("Executing delete staff by id-{}", id);

        Staff response = service.deleteById(id);

        StaffDetailsDto staffDetailsDto = mapper.mapToStaffDetailsDto(response);

        if (response == null) {
            staffDetailsDto.setErrorType(ErrorType.STAFF_NOT_FOUND);
        }

        ResponseEntity<StaffDetailsDto> responseEntity = ResponseEntity.ok(staffDetailsDto);

        log.info("Successfully executed delete staff by id rest API, response entity - {}", responseEntity);
        return responseEntity;
    }
}