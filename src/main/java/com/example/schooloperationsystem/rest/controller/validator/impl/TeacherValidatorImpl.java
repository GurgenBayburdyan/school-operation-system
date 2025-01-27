package com.example.schooloperationsystem.rest.controller.validator.impl;

import com.example.schooloperationsystem.rest.controller.validator.TeacherValidator;
import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.service.StaffService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class TeacherValidatorImpl implements TeacherValidator {

    private final StaffService staffService;

    public Optional<ErrorType> validateCreate(CreateTeacherRequestDto requestDto) {
        log.trace("Executing validate create for request-{}", requestDto);

        if (requestDto.getStaffId() == null) {
            log.trace("Validation failed: Missing staff id");
            return Optional.of(ErrorType.MISSING_STAFF_ID);
        } else if (staffService.getById(requestDto.getStaffId()) == null) {
            log.trace("Validation failed: No staff with id-{}", requestDto.getStaffId());
            return Optional.of(ErrorType.STAFF_NOT_FOUND);
        }

        log.trace("Validation executed successfully");
        return Optional.empty();
    }

}
