package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.facade.validator.PupilInClassValidator;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.SchoolClassService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Optional;
import static org.mockito.Mockito.when;


@AllArgsConstructor
class PupilInClassValidatorImplTest {

    @Mock
    private final PupilService pupilService;

    @Mock
    private final SchoolClassService schoolClassService;

    @InjectMocks
    private final PupilInClassValidator pupilInClassValidator;

    @Test
    void validateCreate_MissingPupilId() {
        CreatePupilInClassRequestDto requestDto = new CreatePupilInClassRequestDto();
        requestDto.setPupilId(null);
        requestDto.setSchoolClassId(1L);
        when(schoolClassService.existsById(1L)).thenReturn(true);
        Optional<ErrorType> errorType = pupilInClassValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.MISSING_PUPIL_ID);
    }

    @Test
    void validateCreate_MissingClassId() {
        CreatePupilInClassRequestDto requestDto = new CreatePupilInClassRequestDto();
        requestDto.setPupilId(1L);
        requestDto.setSchoolClassId(null);
        when(pupilService.existsById(1L)).thenReturn(true);
        Optional<ErrorType> errorType = pupilInClassValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.MISSING_SCHOOL_CLASS_ID);
    }

    @Test
    void validateCreate_PupilNotFound() {
        CreatePupilInClassRequestDto requestDto = new CreatePupilInClassRequestDto();
        requestDto.setPupilId(1L);
        requestDto.setSchoolClassId(1L);
        when(pupilService.existsById(1L)).thenReturn(false);
        when(schoolClassService.existsById(1L)).thenReturn(true);
        Optional<ErrorType> errorType = pupilInClassValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.PUPIL_NOT_FOUND);
    }

    @Test
    void validateCreate_ClassNotFound() {
        CreatePupilInClassRequestDto requestDto = new CreatePupilInClassRequestDto();
        requestDto.setPupilId(1L);
        requestDto.setSchoolClassId(1L);
        when(pupilService.existsById(1L)).thenReturn(true);
        when(schoolClassService.existsById(1L)).thenReturn(false);
        Optional<ErrorType> errorType = pupilInClassValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.CLASS_NOT_FOUND);
    }
}