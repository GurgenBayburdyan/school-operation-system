package com.example.schooloperationsystem.rest.controller.validator;

import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import org.springframework.stereotype.Component;

import java.util.Optional;

//todo add logs in all validator
@Component
public class HeadMasterValidator {

    public Optional<ErrorType> validateCreate(//todo add final keyword here CreateHeadMasterRequestDto requestDto) {
        if (requestDto.getClassId() == null) {
            //todo validate that scool class with the give id exists in database using schoolclassservice
            return Optional.of(ErrorType.MISSING_SCHOOL_CLASS_ID);
        } else if (requestDto.getTeacherId() == null) {
            //todo same here and in all validator
            return Optional.of(ErrorType.MISSING_TEACHER_ID);
        }
        return Optional.empty();
    }

}
