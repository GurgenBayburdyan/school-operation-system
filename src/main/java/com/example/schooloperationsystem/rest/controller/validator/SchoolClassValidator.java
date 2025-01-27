package com.example.schooloperationsystem.rest.controller.validator;

import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;

import java.util.Optional;

public interface SchoolClassValidator {

    Optional<ErrorType> validateCreate(CreateSchoolClassRequestDto requestDto);

}
