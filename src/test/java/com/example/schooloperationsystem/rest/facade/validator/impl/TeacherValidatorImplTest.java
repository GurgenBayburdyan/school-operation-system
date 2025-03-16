package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.service.StaffService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Optional;
import static org.mockito.Mockito.when;

@AllArgsConstructor
class TeacherValidatorImplTest {

    @Mock
    private final StaffService staffService;

    @InjectMocks
    private TeacherValidatorImpl teacherValidator;

    @Test
    void validateCreate_MissingStaffId() {
        CreateTeacherRequestDto requestDto = new CreateTeacherRequestDto();
        requestDto.setStaffId(null);
        when(staffService.existsById(1L)).thenReturn(true);
        Optional<ErrorType> errorType = teacherValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.MISSING_STAFF_ID);
    }

    @Test
    void validateCreate_PupilNotFound() {
        CreateTeacherRequestDto requestDto = new CreateTeacherRequestDto();
        requestDto.setStaffId(1L);
        when(staffService.existsById(1L)).thenReturn(false);
        Optional<ErrorType> errorType = teacherValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.STAFF_NOT_FOUND);
    }
}