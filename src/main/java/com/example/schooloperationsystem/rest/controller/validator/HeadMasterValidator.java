package com.example.schooloperationsystem.rest.controller.validator;

import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HeadMasterValidator {

    public Optional<ErrorType> validateCreate(CreateHeadMasterRequestDto requestDto) {
        if (requestDto.getClassId() == null) {
            return Optional.of(ErrorType.MISSING_SCHOOL_CLASS_ID);
        } else if (requestDto.getTeacherId() == null) {
            return Optional.of(ErrorType.MISSING_TEACHER_ID);
        }
        return Optional.empty();
    }

}
