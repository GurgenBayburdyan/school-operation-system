package com.example.schooloperationsystem.rest.controller.validator;

import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;

import java.util.Optional;

public class PupilInClassValidator {

    public Optional<ErrorType> validateCreate(CreatePupilInClassRequestDto requestDto) {
        if (requestDto.getPupilId() == null) {
            return Optional.of(ErrorType.MISSING_PUPIL_ID);
        } else if (requestDto.getSchoolClassId() == null) {
            return Optional.of(ErrorType.MISSING_SCHOOL_CLASS_ID);
        }
        return Optional.empty();
    }

}
