package com.example.schooloperationsystem.rest.facade.validator;


import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import java.util.Optional;

public interface PupilInClassValidator {

    Optional<ErrorType> validateCreate(CreatePupilInClassRequestDto requestDto);

}
