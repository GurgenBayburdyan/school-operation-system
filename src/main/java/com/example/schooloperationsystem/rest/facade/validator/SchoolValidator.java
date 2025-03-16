package com.example.schooloperationsystem.rest.facade.validator;

import com.example.schooloperationsystem.rest.dto.request.CreateSchoolRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import java.util.Optional;

public interface SchoolValidator {

    Optional<ErrorType> validateCreate(CreateSchoolRequestDto requestDto);
}
