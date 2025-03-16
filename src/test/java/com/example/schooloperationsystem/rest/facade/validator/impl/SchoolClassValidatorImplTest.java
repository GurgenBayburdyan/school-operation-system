package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.SchoolService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Optional;
import static org.mockito.Mockito.when;

@AllArgsConstructor
class SchoolClassValidatorImplTest {

    @Mock
    private final SchoolService schoolService;

    @Mock
    private final SchoolClassService schoolClassService;

    @InjectMocks
    private final SchoolClassValidatorImpl validator;

    @Test
    void validateCreate_MissingGrade() {
        CreateSchoolClassRequestDto requestDto = new CreateSchoolClassRequestDto();
        requestDto.setGrade(null);
        requestDto.setClassLetter('a');
        requestDto.setSchoolId(1L);
        Optional<ErrorType> errorType = validator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.MISSING_GRADE);
    }

    @Test
    void validateCreate_MissingClassLetter() {
        CreateSchoolClassRequestDto requestDto = new CreateSchoolClassRequestDto();
        requestDto.setGrade(3);
        requestDto.setClassLetter(null);
        requestDto.setSchoolId(1L);
        when(schoolClassService.existsById(1L)).thenReturn(true);
        Optional<ErrorType> errorType = validator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.MISSING_CLASS_LETTER);
    }

    @Test
    void validateCreate_MissingSchoolId() {
        CreateSchoolClassRequestDto requestDto = new CreateSchoolClassRequestDto();
        requestDto.setGrade(3);
        requestDto.setClassLetter('a');
        requestDto.setSchoolId(null);
        Optional<ErrorType> errorType = validator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.MISSING_SCHOOL_ID);
    }

    @Test
    void validateCreate_SchoolNotFound() {
        CreateSchoolClassRequestDto requestDto = new CreateSchoolClassRequestDto();
        requestDto.setGrade(3);
        requestDto.setClassLetter('a');
        requestDto.setSchoolId(1L);
        when(schoolService.existsById(1L)).thenReturn(false);
        Optional<ErrorType> errorType = validator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.SCHOOL_NOT_FOUND);
    }
}
