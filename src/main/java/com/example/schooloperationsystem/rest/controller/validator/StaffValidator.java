package com.example.schooloperationsystem.rest.controller.validator;

import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;

import java.util.Optional;

public class StaffValidator {

    public Optional<ErrorType> validateCreate(CreateStaffRequestDto requestDto) {
        if (requestDto.getFirstName() == null) {
            return Optional.of(ErrorType.MISSING_FIRST_NAME);
        } else if (requestDto.getLastName() == null) {
            return Optional.of(ErrorType.MISSING_LAST_NAME);
        } else if (requestDto.getDateOfBirth() == null) {
            return Optional.of(ErrorType.MISSING_DATE_OF_BIRTH);
        }
        return Optional.empty();
    }

}
