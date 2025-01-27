package com.example.schooloperationsystem.rest.controller.validator;

import com.example.schooloperationsystem.rest.dto.request.CreatePupilRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;

import java.util.Optional;

public interface PupilValidator {

    Optional<ErrorType> validateCreate(CreatePupilRequestDto requestDto);

}
