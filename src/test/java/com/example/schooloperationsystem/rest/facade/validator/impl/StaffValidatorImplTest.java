package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.facade.validator.StaffValidator;
import com.example.schooloperationsystem.service.SchoolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StaffValidatorImplTest {

    @Mock
    private SchoolService schoolService;

    private StaffValidator staffValidator;

    @BeforeEach
    void setUp() {
        staffValidator = new StaffValidatorImpl(schoolService);
    }

    @Test
    void validateCreate_MissingFirstName() {
        CreateStaffRequestDto requestDto = new CreateStaffRequestDto();
        requestDto.setFirstName(null);
        requestDto.setLastName("lastName");
        requestDto.setDateOfBirth(LocalDateTime.now());
        requestDto.setSchoolId(1L);
        Optional<ErrorType> errorType = staffValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_FIRST_NAME);
    }

    @Test
    void validateCreate_MissingLastName() {
        CreateStaffRequestDto requestDto = new CreateStaffRequestDto();
        requestDto.setFirstName("firstName");
        requestDto.setLastName(null);
        requestDto.setDateOfBirth(LocalDateTime.now());
        requestDto.setSchoolId(1L);
        Optional<ErrorType> errorType = staffValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_LAST_NAME);
    }

    @Test
    void validateCreate_MissingDateOfBirth() {
        CreateStaffRequestDto requestDto = new CreateStaffRequestDto();
        requestDto.setFirstName("firstName");
        requestDto.setLastName("lastName");
        requestDto.setDateOfBirth(null);
        requestDto.setSchoolId(1L);
        Optional<ErrorType> errorType = staffValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_DATE_OF_BIRTH);
    }

    @Test
    void validateCreate_MissingSchoolId() {
        CreateStaffRequestDto requestDto = new CreateStaffRequestDto();
        requestDto.setFirstName("firstName");
        requestDto.setLastName("lastName");
        requestDto.setDateOfBirth(LocalDateTime.now());
        requestDto.setSchoolId(null);
        Optional<ErrorType> errorType = staffValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_SCHOOL_ID);
    }

    @Test
    void validateCreate_SchoolNotFound() {
        CreateStaffRequestDto requestDto = new CreateStaffRequestDto();
        requestDto.setFirstName("firstName");
        requestDto.setLastName("lastName");
        requestDto.setDateOfBirth(LocalDateTime.now());
        requestDto.setSchoolId(1L);
        when(schoolService.existsById(1L)).thenReturn(false);
        Optional<ErrorType> errorType = staffValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.SCHOOL_NOT_FOUND);

        verify(schoolService).existsById(1L);
    }
}