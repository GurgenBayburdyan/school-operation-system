package com.example.schooloperationsystem.rest.controller.validator.impl;

import com.example.schooloperationsystem.rest.controller.validator.SchoolClassValidator;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.SchoolService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
    //todo make package private
public class SchoolClassValidatorImpl implements SchoolClassValidator {

    private final SchoolService schoolService;
    private final SchoolClassService schoolClassService;

    //todo crate a unit test for validateCreate method.
    //the create SchoolClassValidatorImplUnitTest class in test folder in the same package. 
    //create testValdiateCreate method annotated with @Test annotation.
    //read about mockito, JUnit, assertj. pass CreateSchoolClassRequestDto that has null grade and assert that returned optional of MISSING_GRADE
    //mock shcool service, so the existsById returns false and assert SCHOOL_NOT_FOUND error
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

        if (requestDto.getSchoolId() == null) {
            log.debug("Validation failed: Missing school id");
            return Optional.of(ErrorType.MISSING_SCHOOL_ID);
        }

        if (!schoolService.existsById(requestDto.getSchoolId())) {
            log.debug("Validation failed: No school with id-{}", requestDto.getSchoolId());
            return Optional.of(ErrorType.SCHOOL_NOT_FOUND);
        }

        if (schoolClassService.exists(requestDto.getSchoolId(), requestDto.getGrade(), requestDto.getClassLetter())) {
            log.debug("Validation failed: School Already exists");
            return Optional.of(ErrorType.CLASS_ALREADY_EXISTS);
        }

        log.debug("Validation executed successfully");
        return Optional.empty();
    }

}
