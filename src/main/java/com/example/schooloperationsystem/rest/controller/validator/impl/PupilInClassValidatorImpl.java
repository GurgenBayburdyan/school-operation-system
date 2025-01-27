package com.example.schooloperationsystem.rest.controller.validator.impl;

import com.example.schooloperationsystem.rest.controller.validator.PupilInClassValidator;
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
    //todo must be package private
public class PupilInClassValidatorImpl implements PupilInClassValidator {

    private final PupilService pupilService;
    private final SchoolClassService schoolClassService;

    public Optional<ErrorType> validateCreate(CreatePupilInClassRequestDto requestDto) {
        //todo please change traco to debug
        log.trace("Executing validate create for request-{}", requestDto);

        if (requestDto.getPupilId() == null) {
            log.trace("Validation failed: Missing pupil id");
            return Optional.of(ErrorType.MISSING_PUPIL_ID);
        //todo if else blocks are too much. there is no need to write else, because if will stop the method
            //todo all getById methods must throw an exception if the entity not found
            //todo for now we can replace getById with existsById
        } else if (pupilService.getById(requestDto.getPupilId()) == null) {
            log.trace("Validation failed: No pupil with id-{}", requestDto.getPupilId());
            return Optional.of(ErrorType.PUPIL_NOT_FOUND);
        } else if (requestDto.getSchoolClassId() == null) {
            log.trace("Validation failed: Missing class id");
            return Optional.of(ErrorType.MISSING_SCHOOL_CLASS_ID);
        } else if (schoolClassService.getById(requestDto.getSchoolClassId()) == null) {
            log.trace("Validation failed: No class with id-{}", requestDto.getSchoolClassId());
            return Optional.of(ErrorType.CLASS_NOT_FOUND);
        }

        log.trace("Validation executed successfully");
        return Optional.empty();
    }

}
