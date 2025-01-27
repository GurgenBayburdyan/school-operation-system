package com.example.schooloperationsystem.rest.controller.validator.impl;

import com.example.schooloperationsystem.rest.controller.validator.StaffValidator;
import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class StaffValidatorImpl implements StaffValidator {

    public Optional<ErrorType> validateCreate(CreateStaffRequestDto requestDto) {
        log.trace("Executing validate create for request-{}", requestDto);

        if (requestDto.getFirstName() == null) {
            log.trace("Validation failed: Missing first name");
            return Optional.of(ErrorType.MISSING_FIRST_NAME);
        } else if (requestDto.getLastName() == null) {
            log.trace("Validation failed: Missing last name");
            return Optional.of(ErrorType.MISSING_LAST_NAME);
        } else if (requestDto.getDateOfBirth() == null) {
            log.trace("Validation failed: Missing date of birth");
            return Optional.of(ErrorType.MISSING_DATE_OF_BIRTH);
        }

        log.trace("Validation executed successfully");
        return Optional.empty();
    }

}
