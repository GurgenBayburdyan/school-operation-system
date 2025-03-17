package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeadMasterValidatorImplTest {
    @Mock
    private SchoolClassService schoolClassService;

    @Mock
    private TeacherService teacherService;

    private HeadMasterValidatorImpl headMasterValidator;

    @BeforeEach
    void setUp() {
        headMasterValidator = new HeadMasterValidatorImpl(schoolClassService, teacherService);
    }

    @Test
    void validateCreate_MissingClassId() {
        CreateHeadMasterRequestDto requestDto = new CreateHeadMasterRequestDto();
        requestDto.setTeacherId(1L);
        requestDto.setClassId(null);
        Optional<ErrorType> errorType = headMasterValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_SCHOOL_CLASS_ID);
    }

    @Test
    void validateCreate_MissingTeacherId() {
        CreateHeadMasterRequestDto requestDto = new CreateHeadMasterRequestDto();
        requestDto.setTeacherId(null);
        requestDto.setClassId(1L);
        Optional<ErrorType> errorType = headMasterValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_TEACHER_ID);
    }

    @Test
    void validateCreate_ClassNotFound() {
        CreateHeadMasterRequestDto requestDto = new CreateHeadMasterRequestDto();
        requestDto.setTeacherId(1L);
        requestDto.setClassId(1L);
        when(schoolClassService.existsById(1L)).thenReturn(false);
        Optional<ErrorType> errorType = headMasterValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.CLASS_NOT_FOUND);

        verify(schoolClassService).existsById(1L);
    }

    @Test
    void validateCreate_TeacherNotFound() {
        CreateHeadMasterRequestDto requestDto = new CreateHeadMasterRequestDto();
        requestDto.setTeacherId(1L);
        requestDto.setClassId(1L);
        when(schoolClassService.existsById(1L)).thenReturn(true);
        when(teacherService.existsById(1L)).thenReturn(false);
        Optional<ErrorType> errorType = headMasterValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.TEACHER_NOT_FOUND);

        verify(schoolClassService).existsById(1L);
        verify(teacherService).existsById(1L);
    }
}