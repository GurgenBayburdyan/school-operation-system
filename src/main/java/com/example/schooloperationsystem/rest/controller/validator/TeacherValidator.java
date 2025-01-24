package com.example.schooloperationsystem.rest.controller.validator;

import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;

import java.util.Optional;

public class TeacherValidator {

    public Optional<ErrorType> validateCreate(CreateTeacherRequestDto requestDto) {
        if (requestDto.getStaffId() == null) {
            return Optional.of(ErrorType.MISSING_STAFF_ID);
        }
        return Optional.empty();
    }

}
