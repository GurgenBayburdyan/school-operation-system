package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.facade.validator.HeadMasterValidator;
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
        log.debug("Executing validate create for request-{}", requestDto);

        if (requestDto.getClassId() == null) {
            log.debug("Validation failed: Missing class id");
            return Optional.of(ErrorType.MISSING_SCHOOL_CLASS_ID);
        }

        if (!schoolClassService.existsById(requestDto.getClassId())) {
            log.debug("Validation failed: No class with id-{}", requestDto.getClassId());
            return Optional.of(ErrorType.CLASS_NOT_FOUND);
        }

        if (requestDto.getTeacherId() == null) {
            log.debug("Validation failed: Missing teacher id");
            return Optional.of(ErrorType.MISSING_TEACHER_ID);
        }

        if (!teacherService.existsById(requestDto.getTeacherId())) {
            log.debug("Validation failed: No teacher with id-{}", requestDto.getTeacherId());
            return Optional.of(ErrorType.TEACHER_NOT_FOUND);
        }

        log.debug("Validation executed successfully");
        return Optional.empty();
    }

}
