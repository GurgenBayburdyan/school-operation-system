package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.service.StaffService;
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
class TeacherValidatorImplTest {

    @Mock
    private StaffService staffService;

    private TeacherValidatorImpl teacherValidator;

    @BeforeEach
    void setUp() {
        teacherValidator = new TeacherValidatorImpl(staffService);
    }

    @Test
    void validateCreate_MissingStaffId() {
        CreateTeacherRequestDto requestDto = new CreateTeacherRequestDto();
        requestDto.setStaffId(null);
        Optional<ErrorType> errorType = teacherValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_STAFF_ID);
    }

    @Test
    void validateCreate_StaffNotFound() {
        CreateTeacherRequestDto requestDto = new CreateTeacherRequestDto();
        requestDto.setStaffId(1L);
        when(staffService.existsById(1L)).thenReturn(false);
        Optional<ErrorType> errorType = teacherValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.STAFF_NOT_FOUND);

        verify(staffService).existsById(1L);
    }
}