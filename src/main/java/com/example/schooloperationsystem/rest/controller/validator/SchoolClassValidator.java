package com.example.schooloperationsystem.rest.controller.validator;

import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;

import java.util.Optional;

public class SchoolClassValidator {

    public Optional<ErrorType> validateCreate(CreateSchoolClassRequestDto requestDto) {
        if (requestDto.getGrade() == null) {
            return Optional.of(ErrorType.MISSING_GRADE);
        } else if (requestDto.getClassLetter() == null) {
            return Optional.of(ErrorType.MISSING_CLASS_LETTER);
        }
        return Optional.empty();
    }

}
