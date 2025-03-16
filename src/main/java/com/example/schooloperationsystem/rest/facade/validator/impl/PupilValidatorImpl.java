package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.facade.validator.PupilValidator;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.service.SchoolService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class PupilValidatorImpl implements PupilValidator {

    private final SchoolService schoolService;

    public Optional<ErrorType> validateCreate(CreatePupilRequestDto requestDto) {
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

        if (!schoolService.existsById(requestDto.getSchoolId())) {
            log.debug("Validation failed: No school with id-{}", requestDto.getSchoolId());
            return Optional.of(ErrorType.SCHOOL_NOT_FOUND);
        }

        log.debug("Validation executed successfully");
        return Optional.empty();
    }

}
