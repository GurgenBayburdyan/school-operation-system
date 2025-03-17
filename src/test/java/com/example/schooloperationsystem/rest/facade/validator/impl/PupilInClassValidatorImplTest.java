package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.facade.validator.PupilInClassValidator;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.SchoolClassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PupilInClassValidatorImplTest {

    @Mock
    private PupilService pupilService;

    @Mock
    private SchoolClassService schoolClassService;

    private PupilInClassValidator pupilInClassValidator;

    @BeforeEach
    void setUp() {
        pupilInClassValidator = new PupilInClassValidatorImpl(pupilService, schoolClassService);
    }

    @Test
    void validateCreate_MissingPupilId() {
        CreatePupilInClassRequestDto requestDto = new CreatePupilInClassRequestDto();
        requestDto.setPupilId(null);
        requestDto.setSchoolClassId(1L);
        Optional<ErrorType> errorType = pupilInClassValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_PUPIL_ID);
    }

    @Test
    void validateCreate_MissingClassId() {
        CreatePupilInClassRequestDto requestDto = new CreatePupilInClassRequestDto();
        requestDto.setPupilId(1L);
        requestDto.setSchoolClassId(null);
        Optional<ErrorType> errorType = pupilInClassValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_SCHOOL_CLASS_ID);
    }

    @Test
    void validateCreate_PupilNotFound() {
        CreatePupilInClassRequestDto requestDto = new CreatePupilInClassRequestDto();
        requestDto.setPupilId(1L);
        requestDto.setSchoolClassId(1L);
        when(pupilService.existsById(1L)).thenReturn(false);
        Optional<ErrorType> errorType = pupilInClassValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.PUPIL_NOT_FOUND);

        verify(pupilService).existsById(1L);
    }

    @Test
    void validateCreate_ClassNotFound() {
        CreatePupilInClassRequestDto requestDto = new CreatePupilInClassRequestDto();
        requestDto.setPupilId(1L);
        requestDto.setSchoolClassId(1L);
        when(pupilService.existsById(1L)).thenReturn(true);
        when(schoolClassService.existsById(1L)).thenReturn(false);
        Optional<ErrorType> errorType = pupilInClassValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.CLASS_NOT_FOUND);

        verify(pupilService).existsById(1L);
    }
}