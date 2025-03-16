package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.facade.validator.TeacherValidator;
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
        log.debug("Executing validate create for request-{}", requestDto);

        if (requestDto.getStaffId() == null) {
            log.debug("Validation failed: Missing staff id");
            return Optional.of(ErrorType.MISSING_STAFF_ID);
        }

        if (!staffService.existsById(requestDto.getStaffId())) {
            log.debug("Validation failed: No staff with id-{}", requestDto.getStaffId());
            return Optional.of(ErrorType.STAFF_NOT_FOUND);
        }

        log.debug("Validation executed successfully");
        return Optional.empty();
    }

}
