package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.dto.request.CreatePupilRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.service.SchoolService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.mockito.Mockito.when;

@AllArgsConstructor
class PupilValidatorImplTest {

    @Mock
    private final SchoolService schoolService;

    @InjectMocks
    private final PupilValidatorImpl pupilValidator;

    @Test
    void validateCreate_MissingFirstName() {
        CreatePupilRequestDto requestDto = new CreatePupilRequestDto();
        requestDto.setFirstName(null);
        requestDto.setLastName("lastName");
        requestDto.setDateOfBirth(LocalDateTime.now());
        requestDto.setSchoolId(1L);
        when(schoolService.existsById(1L)).thenReturn(true);
        Optional<ErrorType> errorType = pupilValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.MISSING_FIRST_NAME);
    }

    @Test
    void validateCreate_MissingLastName() {
        CreatePupilRequestDto requestDto = new CreatePupilRequestDto();
        requestDto.setFirstName("firstName");
        requestDto.setLastName(null);
        requestDto.setDateOfBirth(LocalDateTime.now());
        requestDto.setSchoolId(1L);
        when(schoolService.existsById(1L)).thenReturn(true);
        Optional<ErrorType> errorType = pupilValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.MISSING_LAST_NAME);
    }

    @Test
    void validateCreate_MissingDateOfBirth() {
        CreatePupilRequestDto requestDto = new CreatePupilRequestDto();
        requestDto.setFirstName("firstName");
        requestDto.setLastName("lastName");
        requestDto.setDateOfBirth(null);
        requestDto.setSchoolId(1L);
        when(schoolService.existsById(1L)).thenReturn(true);
        Optional<ErrorType> errorType = pupilValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.MISSING_DATE_OF_BIRTH);
    }

    @Test
    void validateCreate_MissingSchoolId() {
        CreatePupilRequestDto requestDto = new CreatePupilRequestDto();
        requestDto.setFirstName("firstName");
        requestDto.setLastName("lastName");
        requestDto.setDateOfBirth(LocalDateTime.now());
        requestDto.setSchoolId(null);
        Optional<ErrorType> errorType = pupilValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.MISSING_SCHOOL_ID);
    }

    @Test
    void validateCreate_SchoolNotFound() {
        CreatePupilRequestDto requestDto = new CreatePupilRequestDto();
        requestDto.setFirstName("firstName");
        requestDto.setLastName("lastName");
        requestDto.setDateOfBirth(LocalDateTime.now());
        requestDto.setSchoolId(1L);
        when(schoolService.existsById(1L)).thenReturn(false);
        Optional<ErrorType> errorType = pupilValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.SCHOOL_NOT_FOUND);
    }
}