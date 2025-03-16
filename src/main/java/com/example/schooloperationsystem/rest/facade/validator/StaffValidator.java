package com.example.schooloperationsystem.rest.facade.validator;


import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import java.util.Optional;

public interface StaffValidator {

    Optional<ErrorType> validateCreate(CreateStaffRequestDto requestDto);

}
