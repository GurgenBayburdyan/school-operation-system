package com.example.schooloperationsystem.rest.controller.validator;

import com.example.schooloperationsystem.rest.dto.request.CreatePupilRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PupilValidator {

    public Optional<ErrorType> validateCreate(CreatePupilRequestDto requestDto) {
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
