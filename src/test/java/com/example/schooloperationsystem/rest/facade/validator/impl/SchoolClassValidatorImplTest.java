package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.SchoolService;
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
class SchoolClassValidatorImplTest {

    @Mock
    private SchoolService schoolService;

    @Mock
    private SchoolClassService schoolClassService;

    private SchoolClassValidatorImpl schoolClassValidator;

    @BeforeEach
    void setUp() {
        schoolClassValidator = new SchoolClassValidatorImpl(schoolService, schoolClassService);
    }

    @Test
    void validateCreate_MissingGrade() {
        CreateSchoolClassRequestDto requestDto = new CreateSchoolClassRequestDto();
        requestDto.setGrade(null);
        requestDto.setClassLetter('a');
        requestDto.setSchoolId(1L);
        Optional<ErrorType> errorType = schoolClassValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_GRADE);
    }

    @Test
    void validateCreate_MissingClassLetter() {
        CreateSchoolClassRequestDto requestDto = new CreateSchoolClassRequestDto();
        requestDto.setGrade(3);
        requestDto.setClassLetter(null);
        requestDto.setSchoolId(1L);
        Optional<ErrorType> errorType = schoolClassValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_CLASS_LETTER);
    }

    @Test
    void validateCreate_MissingSchoolId() {
        CreateSchoolClassRequestDto requestDto = new CreateSchoolClassRequestDto();
        requestDto.setGrade(3);
        requestDto.setClassLetter('a');
        requestDto.setSchoolId(null);
        Optional<ErrorType> errorType = schoolClassValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_SCHOOL_ID);
    }

    @Test
    void validateCreate_SchoolNotFound() {
        CreateSchoolClassRequestDto requestDto = new CreateSchoolClassRequestDto();
        requestDto.setGrade(3);
        requestDto.setClassLetter('a');
        requestDto.setSchoolId(1L);
        when(schoolService.existsById(1L)).thenReturn(false);
        Optional<ErrorType> errorType = schoolClassValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.SCHOOL_NOT_FOUND);

        verify(schoolClassService).existsById(1L);
    }
}
