package com.example.schooloperationsystem.rest.controller.validator.impl;

import com.example.schooloperationsystem.rest.controller.validator.SchoolClassValidator;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class SchoolClassValidatorImpl implements SchoolClassValidator {

    public Optional<ErrorType> validateCreate(CreateSchoolClassRequestDto requestDto) {
        log.debug("Executing validate create for request-{}", requestDto);

        if (requestDto.getGrade() == null) {
            log.debug("Validation failed: Missing grade");
            return Optional.of(ErrorType.MISSING_GRADE);
        }

        if (requestDto.getClassLetter() == null) {
            log.debug("Validation failed: Missing class letter");
            return Optional.of(ErrorType.MISSING_CLASS_LETTER);
        }

        log.debug("Validation executed successfully");
        return Optional.empty();
    }

}
