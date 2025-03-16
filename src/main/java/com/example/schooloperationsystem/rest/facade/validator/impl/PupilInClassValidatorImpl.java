package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.facade.validator.PupilInClassValidator;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.SchoolClassService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
class PupilInClassValidatorImpl implements PupilInClassValidator {

    private final PupilService pupilService;
    private final SchoolClassService schoolClassService;

    public Optional<ErrorType> validateCreate(CreatePupilInClassRequestDto requestDto) {
        log.debug("Executing validate create for request-{}", requestDto);

        if (requestDto.getPupilId() == null) {
            log.debug("Validation failed: Missing pupil id");
            return Optional.of(ErrorType.MISSING_PUPIL_ID);
        }

        if (!pupilService.existsById(requestDto.getPupilId())) {
            log.debug("Validation failed: No pupil with id-{}", requestDto.getPupilId());
            return Optional.of(ErrorType.PUPIL_NOT_FOUND);
        }

        if (requestDto.getSchoolClassId() == null) {
            log.debug("Validation failed: Missing class id");
            return Optional.of(ErrorType.MISSING_SCHOOL_CLASS_ID);
        }

        if (!schoolClassService.existsById(requestDto.getSchoolClassId())) {
            log.debug("Validation failed: No class with id-{}", requestDto.getSchoolClassId());
            return Optional.of(ErrorType.CLASS_NOT_FOUND);
        }

        log.debug("Validation executed successfully");
        return Optional.empty();
    }
}