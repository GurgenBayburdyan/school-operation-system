package com.example.schooloperationsystem.rest.controller.validator.impl;

import com.example.schooloperationsystem.rest.controller.validator.HeadMasterValidator;
import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class HeadMasterValidatorImpl implements HeadMasterValidator {

    private final SchoolClassService schoolClassService;
    private final TeacherService teacherService;

    public Optional<ErrorType> validateCreate(final CreateHeadMasterRequestDto requestDto) {
        log.trace("Executing validate create for request-{}", requestDto);

        if (requestDto.getClassId() == null) {
            log.trace("Validation failed: Missing class id");
            return Optional.of(ErrorType.MISSING_SCHOOL_CLASS_ID);
        } else if (schoolClassService.getById(requestDto.getClassId()) == null) {
            log.trace("Validation failed: No class with id-{}", requestDto.getTeacherId());
            return Optional.of(ErrorType.CLASS_NOT_FOUND);
        } else if (requestDto.getTeacherId() == null) {
            log.trace("Validation failed: Missing teacher id");
            return Optional.of(ErrorType.MISSING_TEACHER_ID);
        } else if (teacherService.getById(requestDto.getTeacherId()) == null) {
            log.trace("Validation failed: No teacher with id-{}", requestDto.getTeacherId());
            return Optional.of(ErrorType.TEACHER_NOT_FOUND);
        }

        log.trace("Validation executed successfully");
        return Optional.empty();
    }

}
