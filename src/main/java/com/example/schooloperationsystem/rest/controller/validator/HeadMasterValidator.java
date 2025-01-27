package com.example.schooloperationsystem.rest.controller.validator;


import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;

import java.util.Optional;

public interface HeadMasterValidator {

    Optional<ErrorType> validateCreate(final CreateHeadMasterRequestDto requestDto);

}
