package com.example.schooloperationsystem.rest.facade.validator;


import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import java.util.Optional;

public interface TeacherValidator {

    Optional<ErrorType> validateCreate(CreateTeacherRequestDto requestDto);

}
