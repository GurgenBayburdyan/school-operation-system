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
        log.debug("Executing validate create for request-{}", requestDto);

        if (requestDto.getFirstName() == null) {
            log.debug("Validation failed: Missing first name");
            return Optional.of(ErrorType.MISSING_FIRST_NAME);
        }

        if (requestDto.getLastName() == null) {
            log.debug("Validation failed: Missing last name");
            return Optional.of(ErrorType.MISSING_LAST_NAME);
        }

        if (requestDto.getDateOfBirth() == null) {
            log.debug("Validation failed: Missing date of birth");
            return Optional.of(ErrorType.MISSING_DATE_OF_BIRTH);
        }

        if (requestDto.getSchoolId() == null) {
            log.debug("Validation failed: Missing school id");
            return Optional.of(ErrorType.MISSING_SCHOOL_ID);
        }

        log.debug("Validation executed successfully");
        return Optional.empty();
    }

}
