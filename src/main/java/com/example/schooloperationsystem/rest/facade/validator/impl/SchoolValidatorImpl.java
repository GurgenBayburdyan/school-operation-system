package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.facade.validator.SchoolValidator;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@Slf4j
public class SchoolValidatorImpl implements SchoolValidator {

    @Override
    public Optional<ErrorType> validateCreate(CreateSchoolRequestDto requestDto) {
        log.debug("Executing validate create for request-{}", requestDto);

        if (requestDto.getAddress() == null) {
            log.debug("Validation failed: Missing address");
            return Optional.of(ErrorType.MISSING_ADDRESS);
        }

        if (requestDto.getNumber() == null) {
            log.debug("Validation failed: Missing number");
            return Optional.of(ErrorType.MISSING_NUMBER);
        }

        if (requestDto.getNamedAfter() == null) {
            log.debug("Validation failed: Missing named after");
            return Optional.of(ErrorType.MISSING_NAMED_AFTER);
        }

        if (requestDto.getPhotoUrl() == null) {
            log.debug("Validation failed: Missing photo url");
            return Optional.of(ErrorType.MISSING_PHOTO_URL);
        }

        log.debug("Validation executed successfully");
        return Optional.empty();
    }
}
