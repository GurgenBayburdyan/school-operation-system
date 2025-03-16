package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.TeacherService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Optional;
import static org.mockito.Mockito.when;

@AllArgsConstructor
class HeadMasterValidatorImplTest {

    @Mock
    private final SchoolClassService schoolClassService;

    @Mock
    private final TeacherService teacherService;

    @InjectMocks
    private final HeadMasterValidatorImpl headMasterValidator;

    @Test
    void validateCreate_MissingClassId() {
        CreateHeadMasterRequestDto requestDto = new CreateHeadMasterRequestDto();
        requestDto.setTeacherId(1L);
        requestDto.setClassId(null);
        when(teacherService.existsById(1L)).thenReturn(true);
        Optional<ErrorType> errorType = headMasterValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.MISSING_SCHOOL_CLASS_ID);
    }

    @Test
    void validateCreate_MissingTeacherId() {
        CreateHeadMasterRequestDto requestDto = new CreateHeadMasterRequestDto();
        requestDto.setTeacherId(null);
        requestDto.setClassId(1L);
        when(schoolClassService.existsById(1L)).thenReturn(true);
        Optional<ErrorType> errorType = headMasterValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.MISSING_TEACHER_ID);
    }

    @Test
    void validateCreate_ClassNotFound() {
        CreateHeadMasterRequestDto requestDto = new CreateHeadMasterRequestDto();
        requestDto.setTeacherId(1L);
        requestDto.setClassId(1L);
        when(schoolClassService.existsById(1L)).thenReturn(false);
        when(teacherService.existsById(1L)).thenReturn(true);
        Optional<ErrorType> errorType = headMasterValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.CLASS_NOT_FOUND);
    }

    @Test
    void validateCreate_TeacherNotFound() {
        CreateHeadMasterRequestDto requestDto = new CreateHeadMasterRequestDto();
        requestDto.setTeacherId(1L);
        requestDto.setClassId(1L);
        when(schoolClassService.existsById(1L)).thenReturn(true);
        when(teacherService.existsById(1L)).thenReturn(false);
        Optional<ErrorType> errorType = headMasterValidator.validateCreate(requestDto);
        assert errorType.isPresent();
        assert errorType.get().equals(ErrorType.TEACHER_NOT_FOUND);
    }
}